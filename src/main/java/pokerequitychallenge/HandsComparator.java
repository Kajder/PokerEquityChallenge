package pokerequitychallenge;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Compares any number of hands (where hand is a list of five cards).
 * Returns list of winning hands (typically in Poker it's a single hand that wins,
 * but draws are possible and therefore considered in this class).
 */

public class HandsComparator {


    public static List<Hand> getWinningHands(List<Hand> handsList) {
        List<Hand> handsWithHighestHandTypesList = getHandsWithHighestHandTypeList(handsList);
        return handsWithHighestHandTypesList.size() == 1 ?
                handsWithHighestHandTypesList :
                calculateWinningHands(handsWithHighestHandTypesList);
    }

    public static HandType getHighestHandType(List<Hand> handsList) {
        List<HandType> handTypes = Arrays.asList(HandType.class.getEnumConstants());
        HandType highestHandType = HandType.HIGH_CARD;
        for (Hand hand : handsList) {
            if (handTypes.indexOf(hand.getHandType()) > handTypes.indexOf(highestHandType)) {
                highestHandType = hand.getHandType();
            }
        }
        return highestHandType;
    }

    public static List<Hand> getHandsWithHighestHandTypeList(List<Hand> handsList) {
        HandType highestHandType = getHighestHandType(handsList);
        List<Hand> handsWithHighestHandTypes = new ArrayList<>();
        for (Hand hand : handsList) {
            if (hand.getHandType() == highestHandType) {
                handsWithHighestHandTypes.add(hand);
            }
        }
        return handsWithHighestHandTypes;
    }


    public static List<Hand> calculateWinningHands(List<Hand> handsWithHighestHandTypeList) {
        HandType handType = handsWithHighestHandTypeList.get(0).getHandType();
        switch (handType) {
            case ROYAL_FLUSH:
                return handsWithHighestHandTypeList;

            case STRAIGHT_FLUSH:
                return findWinnersFrom_StraightFlushes(handsWithHighestHandTypeList);

            case FOUR_OF_A_KIND:
                return findWinnersFrom_FourOfKinds(handsWithHighestHandTypeList);

            case FULL_HOUSE:
                return findWinnersFrom_FullHouses(handsWithHighestHandTypeList);

            case FLUSH:
                return findWinnersFrom_Flushes(handsWithHighestHandTypeList);

            case STRAIGHT:
                return findWinnersFrom_Straights(handsWithHighestHandTypeList);

            case THREE_OF_A_KIND:
                return findWinnersFrom_ThreeOfKinds(handsWithHighestHandTypeList);

            case TWO_PAIR:
                return findWinnersFrom_TwoPairs(handsWithHighestHandTypeList);

            case ONE_PAIR:
                return findWinnersFrom_OnePairs(handsWithHighestHandTypeList);

            case HIGH_CARD:
                return findWinnersFrom_HighCards(handsWithHighestHandTypeList);
        }
        return null;
    }

    //region Straight Flush
    public static List<Hand> findWinnersFrom_StraightFlushes(List<Hand> handsWith_StraightFlushesList) {
        List<Hand> hands = handsWith_StraightFlushesList;
        if (isThereAnyStraightStartingFromAce(handsWith_StraightFlushesList)
                &&
                isThereAnyStraightNotStartingFromAce(handsWith_StraightFlushesList)) {
            hands = handsWith_StraightFlushesList
                    .stream()
                    .filter(hand -> !(hand.getHandTypeCalculator().getSortedHandCards().get(0).getCardType().equals(CardType.A)
                            &&
                            hand.getHandTypeCalculator().getSortedHandCards().get(1).getCardType().equals(CardType.FIVE)))
                    .collect(Collectors.toList());
        }
        short maxCardValue = findGivenMaxCardValueFromHands(hands, 0);
        return hands
                .stream()
                .filter(hand -> hand.getCardValue(0) == maxCardValue)
                .collect(Collectors.toList());
    }

    private static boolean isThereAnyStraightStartingFromAce(List<Hand> handsWith_Straights) {
        return handsWith_Straights
                .stream()
                .anyMatch(hand -> (hand.getHandTypeCalculator().getSortedHandCards().get(0).getCardType().equals(CardType.A)
                        &&
                        hand.getHandTypeCalculator().getSortedHandCards().get(1).getCardType().equals(CardType.FIVE)));
    }

    private static boolean isThereAnyStraightNotStartingFromAce(List<Hand> handsWith_Straights) {
        return handsWith_Straights
                .stream()
                .anyMatch(hand -> !(hand.getHandTypeCalculator().getSortedHandCards().get(0).getCardType().equals(CardType.A)
                        &&
                        hand.getHandTypeCalculator().getSortedHandCards().get(1).getCardType().equals(CardType.FIVE)));
    }
    //endregion

    //region Four Of Kind
    public static List<Hand> findWinnersFrom_FourOfKinds(List<Hand> handsWith_FourOfKindsList) {
        //filter for highest fours only
        Short maxValueOfCommonCards = findDefinedCardMaxValueWithinFourOfKinds(handsWith_FourOfKindsList, false);
        handsWith_FourOfKindsList = filterHandsListForDefinedMaxValueCards(handsWith_FourOfKindsList, maxValueOfCommonCards, false);

        //in case of a draw: find unique card type and filter for it
        if (handsWith_FourOfKindsList.size() > 1) {
            Short maxValueOfUniqueCards = findDefinedCardMaxValueWithinFourOfKinds(handsWith_FourOfKindsList, true);
            handsWith_FourOfKindsList = filterHandsListForDefinedMaxValueCards(handsWith_FourOfKindsList, maxValueOfUniqueCards, true);
        }
        return handsWith_FourOfKindsList;
    }

    public static List<Hand> filterHandsListForDefinedMaxValueCards(List<Hand> handsWith_FourOfKindsList, Short maxValue, boolean unique) {
        List<Hand> highestFoursList = new ArrayList<>();
        Card commonCard;
        for (Hand hand : handsWith_FourOfKindsList) {
            commonCard = findDefinedCardWithinFourOfKinds(hand, unique);
            if (commonCard.getValue() == maxValue) {
                highestFoursList.add(hand);
            }
        }
        return highestFoursList;
    }

    public static Short findDefinedCardMaxValueWithinFourOfKinds(List<Hand> handsWith_FourOfKindsList, boolean unique) {
        Card definedCard;
        Short maxValueOfDefinedCards = 0;

        for (Hand hand : handsWith_FourOfKindsList) {
            definedCard = findDefinedCardWithinFourOfKinds(hand, unique);
            if (definedCard.getValue() > maxValueOfDefinedCards) maxValueOfDefinedCards = definedCard.getValue();
        }
        return maxValueOfDefinedCards;
    }

    public static Card findDefinedCardWithinFourOfKinds(Hand hand, boolean unique) {
        boolean firstEqualThird = hand.getHandTypeCalculator().getSortedHandCards().get(0).getCardType().equals(hand.getHandTypeCalculator().getSortedHandCards().get(2).getCardType());
        if (unique == firstEqualThird) {
            return hand.getHandTypeCalculator().getSortedHandCards().get(4);
        } else {
            return hand.getHandTypeCalculator().getSortedHandCards().get(0);
        }
    }
    //endregion

    //region Full House
    protected static List<Hand> findWinnersFrom_FullHouses(List<Hand> handsWith_FullHousesList) {
        Short highestThreeValue = findHighestThreeValueOfFullHouses(handsWith_FullHousesList);
        List<Hand> handsWith_HighestThrees = findFullHouseHandsWithGivenThreeValue(handsWith_FullHousesList, highestThreeValue);
        Short highestTwoValue = findHighestTwoValueOfFullHouses(handsWith_HighestThrees);
        return findFullHouseHandsWithGivenTwoValue(handsWith_HighestThrees, highestTwoValue);
    }

    private static List<Hand> findFullHouseHandsWithGivenThreeValue(List<Hand> handsWith_FullHousesList, Short highestThreeValue) {
        List<Hand> fullHouseHandsWithGivenThreeValue = new ArrayList<>();
        Short firstCardOfHandValue;
        Short thirdCardOfHandValue;

        for (Hand handWithAThree : handsWith_FullHousesList) {
            firstCardOfHandValue = handWithAThree.getCardValue(0);
            ;
            thirdCardOfHandValue = handWithAThree.getHandTypeCalculator().getSortedHandCards().get(2).getValue();
            if (firstCardOfHandValue.equals(thirdCardOfHandValue)) {
                if (firstCardOfHandValue.equals(highestThreeValue))
                    fullHouseHandsWithGivenThreeValue.add(handWithAThree);
            } else {
                if (thirdCardOfHandValue.equals(highestThreeValue))
                    fullHouseHandsWithGivenThreeValue.add(handWithAThree);
            }
        }

        return fullHouseHandsWithGivenThreeValue;
    }

    private static Short findHighestThreeValueOfFullHouses(List<Hand> handsWith_FullHousesList) {
        Short highestThreeValue = 2;
        Short firstCardOfHandValue;
        Short thirdCardOfHandValue;

        for (Hand handWithAThree : handsWith_FullHousesList) {
            firstCardOfHandValue = handWithAThree.getCardValue(0);
            thirdCardOfHandValue = handWithAThree.getCardValue(2);
            if (firstCardOfHandValue.equals(thirdCardOfHandValue)) {
                if (firstCardOfHandValue > highestThreeValue) highestThreeValue = firstCardOfHandValue;
            } else {
                if (thirdCardOfHandValue > highestThreeValue) highestThreeValue = thirdCardOfHandValue;
            }
        }
        return highestThreeValue;
    }

    private static List<Hand> findFullHouseHandsWithGivenTwoValue(List<Hand> handsWith_FullHousesList, Short highestTwoValue) {
        List<Hand> fullHouseHandsWithGivenTwoValue = new ArrayList<>();
        short firstCardOfHandValue;
        short thirdCardOfHandValue;
        short fourthCardOfHandValue;

        for (Hand handWithATwo : handsWith_FullHousesList) {
            firstCardOfHandValue = handWithATwo.getCardValue(0);
            thirdCardOfHandValue = handWithATwo.getCardValue(2);
            fourthCardOfHandValue = handWithATwo.getCardValue(3);
            if (firstCardOfHandValue == thirdCardOfHandValue) {
                if (fourthCardOfHandValue == highestTwoValue) fullHouseHandsWithGivenTwoValue.add(handWithATwo);
            } else {
                if (firstCardOfHandValue == highestTwoValue) fullHouseHandsWithGivenTwoValue.add(handWithATwo);
            }
        }

        return fullHouseHandsWithGivenTwoValue;
    }

    public static Short findHighestTwoValueOfFullHouses(List<Hand> handsWith_FullHousesList) {
        Short highestTwoValue = 2;
        Short firstCardOfHandValue;
        Short thirdCardOfHandValue;
        Short fourthCardOfHandValue;

        for (Hand handWithATwo : handsWith_FullHousesList) {
            firstCardOfHandValue = handWithATwo.getCardValue(0);
            ;
            thirdCardOfHandValue = handWithATwo.getCardValue(2);
            fourthCardOfHandValue = handWithATwo.getCardValue(3);
            if (firstCardOfHandValue.equals(thirdCardOfHandValue)) {
                if (fourthCardOfHandValue > highestTwoValue) {
                    highestTwoValue = fourthCardOfHandValue;
                }
            } else if (firstCardOfHandValue > highestTwoValue) {
                highestTwoValue = firstCardOfHandValue;
            }
        }
        return highestTwoValue;
    }
    //endregion

    //region Flush
    public static List<Hand> findWinnersFrom_Flushes(List<Hand> handsWith_FlushesList) {
        short maxCardValue;
        for (int i = 0; i < 4; i++) {
            maxCardValue = findGivenMaxCardValueFromHands(handsWith_FlushesList, i);
            for (int j = 0; j < handsWith_FlushesList.size(); j++) {
                if (handsWith_FlushesList.get(j).getHandTypeCalculator().getSortedHandCards().get(i).getValue()
                        != maxCardValue) {
                    handsWith_FlushesList.remove(j);
                    if (handsWith_FlushesList.size() == 1) {
                        return handsWith_FlushesList;
                    }
                    j--;
                }
            }
        }
        return handsWith_FlushesList;
    }

    private static short findGivenMaxCardValueFromHands(List<Hand> hands, int i) {
        short max = hands
                .stream()
                .map(hand -> hand.getHandTypeCalculator().getSortedHandCards().get(i).getValue())
                .max(Comparator.comparing(Integer::valueOf))
                .get();
        return max;
    }
    //endregion

    //region Straight
    public static List<Hand> findWinnersFrom_Straights(List<Hand> handsWith_Straights) {
        return findWinnersFrom_StraightFlushes(handsWith_Straights);
    }
    //endregion

    //region Three Of Kind
    public static List<Hand> findWinnersFrom_ThreeOfKinds(List<Hand> handsWith_ThreeOfKinds) {
        short maxThreeValue = findHighestThreeValueOfFullHouses(handsWith_ThreeOfKinds);
        List<Hand> handsWith_HighestThree = findFullHouseHandsWithGivenThreeValue(
                handsWith_ThreeOfKinds, maxThreeValue);
        List<Short> valuesToSkip = new ArrayList<>();
        valuesToSkip.add(maxThreeValue);
        if (handsWith_HighestThree.size() == 1) {
            return handsWith_HighestThree;
        }

        short maxNonThreeValue = findCardMaxValueFromHandsDifferentThanGivenValue(
                handsWith_HighestThree,
                valuesToSkip);
        valuesToSkip.add(maxNonThreeValue);
        List<Hand> handsWith_HighestThreeAndHighestSingleCard = handsWith_HighestThree
                .stream()
                .filter(hand -> isThereCardWithGivenValueWithinHand(hand, maxNonThreeValue))
                .collect(Collectors.toList());

        if (handsWith_HighestThreeAndHighestSingleCard.size() == 1) {
            return handsWith_HighestThreeAndHighestSingleCard;
        }

        short maxNonThreeValueAmongHandsLeft = findCardMaxValueFromHandsDifferentThanGivenValue(
                handsWith_HighestThreeAndHighestSingleCard, valuesToSkip);

        return handsWith_HighestThreeAndHighestSingleCard
                .stream()
                .filter(hand -> isThereCardWithGivenValueWithinHand(hand, maxNonThreeValueAmongHandsLeft))
                .collect(Collectors.toList());
    }

    private static boolean isThereCardWithGivenValueWithinHand(Hand hand, short cardValue) {
        return hand.getHandCards()
                .stream()
                .anyMatch(card -> card.getValue() == cardValue);
    }

    private static short findCardMaxValueFromHandsDifferentThanGivenValue(List<Hand> hands, List<Short> valuesToSkip) {
        short maxNonThreeValue = 2;
        short cardValue;
        for (Hand hand : hands) {
            for (int j = 0; j < 5; j++) {
                cardValue = hand.getHandCards().get(j).getValue();
                if ((cardValue > maxNonThreeValue) && (!valuesToSkip.contains(cardValue))) {
                    maxNonThreeValue = cardValue;
                }
            }
        }
        return maxNonThreeValue;
    }
    //endregion

    //region Two Pairs
    public static List<Hand> findWinnersFrom_TwoPairs(List<Hand> handsWith_TwoPairs) {
        short highestPairValue = findHighestValueOfGivenCardsFromTwoPairHands(
                handsWith_TwoPairs,
                (short) 0,
                (short) 1);
        List<Hand> handsWithHighestHigherPair = filterForHandsWithGivenValueOfGivenCards(
                handsWith_TwoPairs,
                highestPairValue,
                (short) 1);
        handsWithHighestHigherPair.forEach(Hand::printHand);
        if (handsWithHighestHigherPair.size() == 1) {
            return handsWithHighestHigherPair;
        }

        short highestFromSecondaryPairsValue = findHighestValueOfGivenCardsFromTwoPairHands(
                handsWithHighestHigherPair,
                (short) 2,
                (short) 3);
        List<Hand> handsWithHighestLowerPair = filterForHandsWithGivenValueOfGivenCards(
                handsWithHighestHigherPair,
                highestFromSecondaryPairsValue,
                (short) 3);
        handsWithHighestHigherPair.forEach(Hand::printHand);
        if (handsWithHighestLowerPair.size() == 1) {
            return handsWithHighestLowerPair;
        }
        short highestFromSingleCards = findHighestValueOfSingleCards(
                handsWithHighestLowerPair,
                highestPairValue,
                highestFromSecondaryPairsValue);
        return filterForTwoPairHandsWithHighestSingleCard(handsWithHighestLowerPair, highestFromSingleCards);
    }

    private static short findHighestValueOfGivenCardsFromTwoPairHands(
            List<Hand> hands,
            short cardIndex_1,
            short cardIndex_2) {
        short higherPairValue;
        short highestPairValue = 0;
        for (Hand hand : hands) {
            if (hand.getCardValue(cardIndex_1) == hand.getCardValue(cardIndex_2)) {
                higherPairValue = hand.getCardValue(cardIndex_1);
            } else {
                higherPairValue = hand.getCardValue(cardIndex_2);
            }
            if (higherPairValue > highestPairValue) {
                highestPairValue = higherPairValue;
            }
        }
        return highestPairValue;
    }

    private static List<Hand> filterForHandsWithGivenValueOfGivenCards(
            List<Hand> hands,
            short cardValue,
            short cardIndex_1) {
        return hands
                .stream()
                .filter(hand -> hand.getCardValue(cardIndex_1) == cardValue)
                .collect(Collectors.toList());
    }

    private static short findHighestValueOfSingleCards(
            List<Hand> hands,
            short higherPairValue,
            short lowerPairValue) {
        short highestSingleCardValue = 0;
        short singleCardValue;
        for (Hand hand : hands) {
            singleCardValue = hand.getHandCards()
                    .stream()
                    .filter(card -> ((card.getValue() != higherPairValue)
                            &&
                            (card.getValue() != lowerPairValue)))
                    .findFirst()
                    .get()
                    .getValue();
            if (singleCardValue > highestSingleCardValue) {
                highestSingleCardValue = singleCardValue;
            }
        }
        return highestSingleCardValue;
    }

    private static List<Hand> filterForTwoPairHandsWithHighestSingleCard(List<Hand> hands, short singleCardValue) {
        return hands
                .stream()
                .filter(hand -> hand.getCardValue(0) == singleCardValue
                        || hand.getCardValue(2) == singleCardValue
                        || hand.getCardValue(4) == singleCardValue)
                .collect(Collectors.toList());
    }
    //endregion

    //region One Pair
    public static List<Hand> findWinnersFrom_OnePairs(List<Hand> handsWith_OnePair) {
        Set<Short> valuesToSkipWhenFilteringForHighest = new HashSet<>();
        //highest pair
        short highestPairValue = findHighestPairValue(handsWith_OnePair);
        List<Hand> handsWithHighestPair = filterForHandsWithGivenPairValue(
                handsWith_OnePair,
                highestPairValue);
        handsWithHighestPair.forEach(Hand::printHand);
        if (handsWithHighestPair.size() == 1) {
            return handsWithHighestPair;
        }

        //highest single card
        valuesToSkipWhenFilteringForHighest.add(highestPairValue);
        short highestSingleCardValueDifferentThanThePair = findHighestCardValueDifferentThanGivenValues(
                handsWithHighestPair,
                valuesToSkipWhenFilteringForHighest);
        List<Hand> handsWithHighestSingleCardOfCardsWithHighestPair = filterForHandsWithGivenSingleCardValue(
                handsWithHighestPair,
                highestSingleCardValueDifferentThanThePair);
        if (handsWithHighestSingleCardOfCardsWithHighestPair.size() == 1) {
            return handsWithHighestSingleCardOfCardsWithHighestPair;
        }

        //second highest single card
        valuesToSkipWhenFilteringForHighest.add(highestSingleCardValueDifferentThanThePair);
        short secondHighestSingleCardValue = findHighestCardValueDifferentThanGivenValues(
                handsWithHighestSingleCardOfCardsWithHighestPair,
                valuesToSkipWhenFilteringForHighest);
        List<Hand> handsWithTwoHighestSingleCardsOfCardsWithHighestPair = filterForHandsWithGivenSingleCardValue(
                handsWithHighestSingleCardOfCardsWithHighestPair,
                secondHighestSingleCardValue);
        if (handsWithTwoHighestSingleCardsOfCardsWithHighestPair.size() == 1) {
            return handsWithTwoHighestSingleCardsOfCardsWithHighestPair;
        }

        //third highest single card
        valuesToSkipWhenFilteringForHighest.add(secondHighestSingleCardValue);
        short thirdHighestSingleCardValue = findHighestCardValueDifferentThanGivenValues(
                handsWithTwoHighestSingleCardsOfCardsWithHighestPair,
                valuesToSkipWhenFilteringForHighest);
        return filterForHandsWithGivenSingleCardValue(
                handsWithTwoHighestSingleCardsOfCardsWithHighestPair,
                thirdHighestSingleCardValue);
    }

    private static short findHighestPairValue(List<Hand> hands) {
        short pairValue;
        short highestPairValue = 0;
        for (Hand hand : hands) {
            pairValue = findPairValueWithinOnePairHand(hand);
            if (pairValue > highestPairValue) {
                highestPairValue = pairValue;
            }
        }
        return highestPairValue;
    }

    private static short findCountOfGivenCardValueWithinGivenHand(Hand hand, short cardValue) {
        return (short) hand.getHandCards()
                .stream()
                .map(Card::getValue)
                .filter(v -> v == (cardValue))
                .count();
    }

    private static List<Hand> filterForHandsWithGivenPairValue(List<Hand> hands, short pairValue) {
        return hands
                .stream()
                .filter(hand -> findCountOfGivenCardValueWithinGivenHand(hand, pairValue) == 2)
                .collect(Collectors.toList());
    }

    private static short findPairValueWithinOnePairHand(Hand hand) {
        short cardValue;
        for (short i = 0; i < 5; i++) {
            cardValue = hand.getCardValue(i);
            if (findCountOfGivenCardValueWithinGivenHand(hand, cardValue) == 2) {
                return hand.getCardValue(i);
            }
        }
        throw new RuntimeException("No pair found within One Pair hand");
    }

    private static short findHighestCardValueDifferentThanGivenValues(List<Hand> hands, Set<Short> values) {
        Set<Short> allCardValues = new HashSet<>();
        for (Hand hand : hands) {
            allCardValues.addAll(hand.getHandCards().stream().map(Card::getValue).collect(Collectors.toSet()));
        }
        allCardValues.removeAll(values);
        return Collections.max(allCardValues);
    }

    private static List<Hand> filterForHandsWithGivenSingleCardValue(List<Hand> hands, short cardValue) {
        return hands
                .stream()
                .filter(hand -> findCountOfGivenCardValueWithinGivenHand(hand, cardValue) == 1)
                .collect(Collectors.toList());
    }
    //endregion

    //region Highest Card
    public static List<Hand> findWinnersFrom_HighCards(List<Hand> handsWith_HighCards) {
        Set<Short> valuesToSkipWhenFilteringForHighest = new HashSet<>();
        List<Hand> hands = handsWith_HighCards;
        short nextHighestCardValue;

        for (int i = 0; i < 5; i++) {
            nextHighestCardValue = findHighestCardValueDifferentThanGivenValues(
                    hands,
                    valuesToSkipWhenFilteringForHighest);
            valuesToSkipWhenFilteringForHighest.add(nextHighestCardValue);
            hands = filterForHandsWithGivenSingleCardValue(
                    hands,
                    nextHighestCardValue);
            if (hands.size() == 1) return hands;
        }
        return hands;
    }
    //endregion
}

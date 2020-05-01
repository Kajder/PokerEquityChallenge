package pokerequitychallenge.hand;

import pokerequitychallenge.Exception.HandNotFound;
import pokerequitychallenge.card.Card;
import pokerequitychallenge.card.CardType;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Compares any number of hands (where hand is a list of five cards).
 * Returns list of winning hands (typically in Poker it's a single hand that wins,
 * but draws are possible and therefore considered in this class).
 */

public class HandsComparator {

    private static final int STRAIGHT_FROM_ACE_CARDS_VALUES_SUM = 28;
    private static final int STRAIGHT_FROM_THREE_CARDS_VALUES_SUM = 25;
    private static final int STRAIGHT_FROM_TWO_CARDS_VALUES_SUM = 20;

    public static List<Hand> getWinningHands(List<Hand> handsList) {
        List<Hand> handsWithHighestHandTypesList = getHandsWithHighestHandTypeList(handsList);
        return handsWithHighestHandTypesList.size() == 1
                ? handsWithHighestHandTypesList
                : calculateWinningHands(handsWithHighestHandTypesList);
    }

    public static HandType getHighestHandType(List<Hand> handsList) {
        return handsList.stream()
                .max(Comparator.naturalOrder())
                .orElseThrow(HandNotFound::new)
                .getHandType();
    }

    public static List<Hand> getHandsWithHighestHandTypeList(List<Hand> handsList) {
        HandType highestHandType = getHighestHandType(handsList);
        return handsList.stream()
                .filter(hand -> hand.getHandType().equals(highestHandType))
                .collect(Collectors.toList());
    }


    public static List<Hand> calculateWinningHands(List<Hand> handsWithHighestHandTypeList) {
        handsWithHighestHandTypeList.forEach(Hand::setUtils);
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
        int cardsValuesMaxSumFromStraightHands = findCardsValuesMaxSumFromStraightFlushHands(
                handsWith_StraightFlushesList);
        return handsWith_StraightFlushesList
                .stream()
                .filter(h -> h.getHandCardsValuesSum() == cardsValuesMaxSumFromStraightHands)
                .collect(Collectors.toList());
    }

    private static int findCardsValuesMaxSumFromStraightFlushHands(List<Hand> handsWith_StraightFlushesList) {
        int highestStraightCardsValuesSum = handsWith_StraightFlushesList
                .stream()
                .map(Hand::getHandCardsValuesSum)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new RuntimeException("Highest sum of hands cards could not be computed"));

        if (highestStraightCardsValuesSum == STRAIGHT_FROM_ACE_CARDS_VALUES_SUM) {
            if (handsWith_StraightFlushesList.stream().anyMatch(h -> h.getHandCardsValuesSum() == STRAIGHT_FROM_THREE_CARDS_VALUES_SUM))
                return STRAIGHT_FROM_THREE_CARDS_VALUES_SUM;
            if (handsWith_StraightFlushesList.stream().anyMatch(h -> h.getHandCardsValuesSum() == STRAIGHT_FROM_TWO_CARDS_VALUES_SUM))
                return STRAIGHT_FROM_TWO_CARDS_VALUES_SUM;
            return STRAIGHT_FROM_ACE_CARDS_VALUES_SUM;
        } else {
            return highestStraightCardsValuesSum;
        }
    }
    //endregion

    //region Four Of Kind
    public static List<Hand> findWinnersFrom_FourOfKinds(List<Hand> handsWith_FourOfKindsList) {
        CardType highestCardTypeOfCardsOccurringFourTimesInHand = findHighestTypeOfCardsOccurringNTimesAmongHands(
                handsWith_FourOfKindsList, 4L);

        int cardsValuesMaxSumForHighestFourHands = findCardsValuesMaxSumOfHandsWithGivenCardTypeOccurringNTimes(
                handsWith_FourOfKindsList, highestCardTypeOfCardsOccurringFourTimesInHand, 4L);

        return handsWith_FourOfKindsList
                .stream()
                .filter(h -> h.getHandCardsTypesOccurrences().get(highestCardTypeOfCardsOccurringFourTimesInHand).equals(4L))
                .filter(h -> h.getHandCardsValuesSum() == cardsValuesMaxSumForHighestFourHands)
                .collect(Collectors.toList());
    }
    //endregion

    //region Full House
    protected static List<Hand> findWinnersFrom_FullHouses(List<Hand> handsWith_FullHousesList) {
        CardType highestCardTypeOfCardsOccurringThreeTimesInHands = findHighestTypeOfCardsOccurringNTimesAmongHands(
                handsWith_FullHousesList, 3L);

        int cardsValuesMaxSumForHighestThreeHands = findCardsValuesMaxSumOfHandsWithGivenCardTypeOccurringNTimes(
                handsWith_FullHousesList, highestCardTypeOfCardsOccurringThreeTimesInHands, 3L);

        return handsWith_FullHousesList
                .stream()
                .filter(h -> h.getHandCardsTypesOccurrences().containsKey(highestCardTypeOfCardsOccurringThreeTimesInHands)
                        &&
                        h.getHandCardsTypesOccurrences().get(highestCardTypeOfCardsOccurringThreeTimesInHands).equals(3L))
                .filter(h -> h.getHandCardsValuesSum() == cardsValuesMaxSumForHighestThreeHands)
                .collect(Collectors.toList());
    }
    //endregion

    //region Flush
    public static List<Hand> findWinnersFrom_Flushes(List<Hand> handsWith_FlushesList) {
        return findWinnersFrom_HighCards(handsWith_FlushesList);
    }
    //endregion

    //region Straight
    public static List<Hand> findWinnersFrom_Straights(List<Hand> handsWith_Straights) {
        return findWinnersFrom_StraightFlushes(handsWith_Straights);
    }
    //endregion

    //region Three Of Kind
    public static List<Hand> findWinnersFrom_ThreeOfKinds(List<Hand> handsWith_ThreeOfKinds) {
        handsWith_ThreeOfKinds.forEach(h -> h.setNonFigureCardsValue(calculateNonFiguredCardsValuesSum(h)));

        CardType highestThreeCardType = findHighestTypeOfCardsOccurringNTimesAmongHands(handsWith_ThreeOfKinds, 3L);

        int highestNonFiguredCardsSum = findMaxNonFiguredCardsValuesSumAmongHands(
                handsWith_ThreeOfKinds
                        .stream()
                        .filter(h -> h.getHandCardsTypesOccurrences().containsKey(highestThreeCardType)
                                && h.getHandCardsTypesOccurrences().get(highestThreeCardType).equals(3L))
                        .collect(Collectors.toList()));

        return handsWith_ThreeOfKinds
                .stream()
                .filter(h -> h.getHandCardsTypesOccurrences().containsKey(highestThreeCardType)
                        && h.getHandCardsTypesOccurrences().get(highestThreeCardType).equals(3L))
                .filter(h -> h.getNonFigureCardsValue() == highestNonFiguredCardsSum)
                .collect(Collectors.toList());
    }
    //endregion

    //region Two Pairs
    public static List<Hand> findWinnersFrom_TwoPairs(List<Hand> handsWith_TwoPairs) {
        CardType highestPairCardType = findHighestTypeOfCardsOccurringNTimesAmongHands(handsWith_TwoPairs, 2L);

        CardType highestSecondPairCardTypeFromHandsWithHighestPair = findHighestPairCardTypeOtherThanGivenFromHands(
                handsWith_TwoPairs, highestPairCardType);

        int cardsValuesMaxSumOfHandsWithHighestPairAndHighestSecondPair = findCardsValuesMaxSumOfHandsWithHighestPairAndHighestSecondPair(
                handsWith_TwoPairs, highestPairCardType, highestSecondPairCardTypeFromHandsWithHighestPair);

        return handsWith_TwoPairs
                .stream()
                .filter(h -> h.getHandCardsTypesOccurrences().containsKey(highestPairCardType)
                        && h.getHandCardsTypesOccurrences().get(highestPairCardType).equals(2L))
                .filter(h -> h.getHandCardsTypesOccurrences().containsKey(highestSecondPairCardTypeFromHandsWithHighestPair)
                        && h.getHandCardsTypesOccurrences().get(highestSecondPairCardTypeFromHandsWithHighestPair).equals(2L))
                .filter(h -> h.getHandCardsValuesSum() == cardsValuesMaxSumOfHandsWithHighestPairAndHighestSecondPair)
                .collect(Collectors.toList());
    }

    private static CardType findHighestPairCardTypeOtherThanGivenFromHands(List<Hand> hands, CardType highestPairCardType) {
        return hands
                .stream()
                .filter(h -> h.getHandCardsTypesOccurrences().containsKey(highestPairCardType)
                        && h.getHandCardsTypesOccurrences().get(highestPairCardType).equals(2L))
                .flatMap(h -> h.getHandCards()
                        .stream()
                        .filter(c -> h.getHandCardsTypesOccurrences().get(c.getCardType()).equals(2L))
                        .map(Card::getCardType))
                .filter(t -> !t.equals(highestPairCardType))
                .reduce((a, b) -> a.value > b.value ? a : b)
                .orElseThrow(() -> new RuntimeException("Second highest pair cards type of hands with highest pair was not found"));
    }

    private static int findCardsValuesMaxSumOfHandsWithHighestPairAndHighestSecondPair(List<Hand> hands,
                                                                                       CardType highestPairCardType,
                                                                                       CardType highestSecondPairCardType) {
        return hands
                .stream()
                .filter(h -> h.getHandCardsTypesOccurrences().containsKey(highestPairCardType)
                        && h.getHandCardsTypesOccurrences().get(highestPairCardType).equals(2L))
                .filter(h -> h.getHandCardsTypesOccurrences().containsKey(highestSecondPairCardType)
                        && h.getHandCardsTypesOccurrences().get(highestSecondPairCardType).equals(2L))
                .map(Hand::getHandCardsValuesSum)
                .max(Integer::compare)
                .orElseThrow(() -> new RuntimeException("Could not find max sum of hands with two highest pairs"));
    }
    //endregion

    //region One Pair
    public static List<Hand> findWinnersFrom_OnePairs(List<Hand> handsWith_OnePair) {
        handsWith_OnePair.forEach(h -> h.setNonFigureCardsValue(calculateNonFiguredCardsValuesSum(h)));

        CardType highestPairCardType = findHighestTypeOfCardsOccurringNTimesAmongHands(handsWith_OnePair, 2L);

        int highestNonFiguredCardsSum = findMaxNonFiguredCardsValuesSumAmongHands(
                handsWith_OnePair
                        .stream()
                        .filter(h -> h.getHandCardsTypesOccurrences().containsKey(highestPairCardType)
                                && h.getHandCardsTypesOccurrences().get(highestPairCardType).equals(2L))
                        .collect(Collectors.toList()));

        return handsWith_OnePair
                .stream()
                .filter(h -> h.getHandCardsTypesOccurrences().containsKey(highestPairCardType)
                        && h.getHandCardsTypesOccurrences().get(highestPairCardType).equals(2L))
                .filter(h -> h.getNonFigureCardsValue() == highestNonFiguredCardsSum)
                .collect(Collectors.toList());
    }
    //endregion

    //region Highest Card
    public static List<Hand> findWinnersFrom_HighCards(List<Hand> handsWith_HighCards) {
        handsWith_HighCards.forEach(h -> h.setNonFigureCardsValue(calculateNonFiguredCardsValuesSum(h)));

        int highestNonFiguredCardsSum = findMaxNonFiguredCardsValuesSumAmongHands(handsWith_HighCards);

        return handsWith_HighCards
                .stream()
                .filter(h -> h.getNonFigureCardsValue() == highestNonFiguredCardsSum)
                .collect(Collectors.toList());
    }
    //endregion

    private static CardType findHighestTypeOfCardsOccurringNTimesAmongHands(List<Hand> handsWith_FourOfKindsList, long n) {
        return handsWith_FourOfKindsList
                .stream()
                .map(h -> h.getHandCardsTypesOccurrences().entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(n))
                        .map(Map.Entry::getKey)
                        .reduce((a, b) -> a.value > b.value ? a : b)
                        .orElseThrow(() -> new RuntimeException(
                                String.format("Card Type with %d occurrences not found", n))))
                .reduce((a, b) -> a.value > b.value ? a : b)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Max Card Type of cards occurring %d times was not found", n)));
    }

    private static int findCardsValuesMaxSumOfHandsWithGivenCardTypeOccurringNTimes(
            List<Hand> hands, CardType highestCardTypeOccurringNTimes, long n) {
        return hands.stream()
                .filter(h -> h.getHandCardsTypesOccurrences().containsKey(highestCardTypeOccurringNTimes)
                        &&
                        h.getHandCardsTypesOccurrences().get(highestCardTypeOccurringNTimes).equals(n))
                .map(Hand::getHandCardsValuesSum)
                .max(Integer::compare)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Max sum of cards values for hand with highest %d cards was not found", n)));
    }

    private static int calculateNonFiguredCardsValuesSum(Hand hand) {
        if (!List.of(HandType.HIGH_CARD, HandType.ONE_PAIR, HandType.THREE_OF_A_KIND, HandType.FLUSH).contains(hand.getHandType())) {
            throw new RuntimeException("Hand Type not appropriate for calculation of non figured cards values sum");
        }
        if (null == hand.getHandCardsTypesOccurrences()) {
            throw new RuntimeException("Hand Cards Types Occurrences map has not been created");
        }
        List<Integer> singleNonFiguredCards = hand.getHandCardsTypesOccurrences().entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(1L))
                .map(entry -> entry.getKey().value)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        int nonFigureCardsValuesSum = 0;
        for (int i = 0; i < singleNonFiguredCards.size(); i++) {
            nonFigureCardsValuesSum = nonFigureCardsValuesSum + (int) (singleNonFiguredCards.get(i) * Math.pow(14, i));
        }
        return nonFigureCardsValuesSum;
    }

    private static int findMaxNonFiguredCardsValuesSumAmongHands(List<Hand> hands) {
        return hands
                .stream()
                .map(Hand::getNonFigureCardsValue)
                .max(Integer::compareTo)
                .orElseThrow(() -> new RuntimeException("Highest Non Figured Cards Sum was not found"));
    }
}
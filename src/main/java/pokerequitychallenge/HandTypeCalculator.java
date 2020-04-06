package pokerequitychallenge;

import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class HandTypeCalculator {
    private static List<Card> handCards;
    private static List<Card> sortedHandCards;
    private static Map<CardType, Integer> cardTypesCountersMap;
    private static boolean isRoyalFlush;
    private static boolean isFlush;
    private static boolean isStraight;
    private static boolean isFourOfKind;
    private static boolean isThreeOfKind;
    private static boolean isFullHouse;
    private static Integer numberOfPairs;

    static HandType calculateHandType(List<Card> cards) {
        //TODO sprawdzenie liczby kart
        resetClassVariables();
        handCards = cards;
        sortedHandCards = handCards.stream().sorted().collect(Collectors.toList());

        isFlush = isFlush();
        isStraight = isStraight();
        isRoyalFlush = isRoyalFlush();
        if (isRoyalFlush) return HandType.ROYAL_FLUSH;

        if (isStraight && isFlush) return HandType.STRAIGHT_FLUSH;

        checkForCardTypesMultiplications();

        isFourOfKind = isFourOfKind();
        if (isFourOfKind) return HandType.FOUR_OF_A_KIND;

        isFullHouse = isFullHouse();
        if (isFullHouse) return HandType.FULL_HOUSE;

        if (isFlush) return HandType.FLUSH;

        if (isStraight) return HandType.STRAIGHT;

        isThreeOfKind = isThreeOfKind();
        if (isThreeOfKind) return HandType.THREE_OF_A_KIND;

        numberOfPairs = calculateNumberOfPairs();

        if (numberOfPairs == 2) return HandType.TWO_PAIR;

        if (numberOfPairs == 1) return HandType.ONE_PAIR;

        return HandType.HIGH_CARD;
    }

    private static void resetClassVariables() {
        cardTypesCountersMap = null;
        isRoyalFlush = false;
        isFlush = false;
        isStraight = false;
        isFourOfKind = false;
        isThreeOfKind = false;
        isFullHouse = false;
        numberOfPairs = null;
    }

    private static void checkForCardTypesMultiplications() {
        cardTypesCountersMap = new HashMap<>();
        CardType[] cardTypes = CardType.class.getEnumConstants();

        Integer currentCount = 0;
        for (CardType cardType : cardTypes) {
            cardTypesCountersMap.put(cardType, 0);
        }
        for (Card card : handCards) {
            currentCount = cardTypesCountersMap.get(card.getCardType());
            currentCount++;
            cardTypesCountersMap.put(card.getCardType(), currentCount);
        }
    }

    private static boolean isFullHouse() {
        return cardTypesCountersMap.containsValue(3) && cardTypesCountersMap.containsValue(2);
    }

    private static boolean isFourOfKind() {
        return cardTypesCountersMap.containsValue(4);
    }

    private static boolean isThreeOfKind() {
        return cardTypesCountersMap.containsValue(3);
    }

    private static Integer calculateNumberOfPairs() {
        Integer pairsCounter = 0;
        Collection<Integer> mapValues = cardTypesCountersMap.values();
        for (Integer value : mapValues) {
            if (value == 2) pairsCounter++;
        }
        return pairsCounter;
    }

    private static boolean isRoyalFlush() {
        return isFlush && isStraight
                && sortedHandCards.get(0).getCardType().equals(CardType.A)
                && sortedHandCards.get(1).getCardType().equals(CardType.K);
    }

    private static boolean isFlush() {
        CardColor cardColor = sortedHandCards.get(0).getCardColor();
        for (int i = 1; i < sortedHandCards.size(); i++) {
            if (!sortedHandCards.get(i).getCardColor().equals(cardColor)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isStraight() {
        //is it Straight from Ace
        if (sortedHandCards.get(0).getCardType().equals(CardType.A)
                && sortedHandCards.get(1).getCardType().equals(CardType.FIVE)
                && sortedHandCards.get(2).getCardType().equals(CardType.FOUR)
                && sortedHandCards.get(3).getCardType().equals(CardType.THREE)
                && sortedHandCards.get(4).getCardType().equals(CardType.TWO)) {
            return true;
        }
        //is it any other Straight
        short maxCardValue = sortedHandCards.get(0).getValue();
        for (int i = 1; i < handCards.size(); i++) {
            if (sortedHandCards.get(i).getValue() != (maxCardValue - i)) return false;
        }
        return true;
    }
}

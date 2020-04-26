package pokerequitychallenge;

import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Getter
public class HandTypeCalculator {
    private static List<Card> handCards;
    private static Map<CardType, Long> cardTypesCountersMap;
    private static boolean isRoyalFlush;
    private static boolean isFlush;
    private static boolean isStraight;
    private static boolean isFourOfKind;
    private static boolean isThreeOfKind;
    private static boolean isFullHouse;
    private static Integer numberOfPairs;

    static HandType calculateHandType(List<Card> cards) {
        resetClassVariables();
        handCards = cards.stream().sorted().collect(Collectors.toList());

        isFlush = isFlush();
        isStraight = isStraight();
        isRoyalFlush = isRoyalFlush();
        if (isRoyalFlush) return HandType.ROYAL_FLUSH;

        if (isStraight && isFlush) return HandType.STRAIGHT_FLUSH;

        cardTypesCountersMap = handCards.stream().collect(groupingBy(Card::getCardType, Collectors.counting()));

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

    private static boolean isFullHouse() {
        return cardTypesCountersMap.containsValue(3L) && cardTypesCountersMap.containsValue(2L);
    }

    private static boolean isFourOfKind() {
        return cardTypesCountersMap.containsValue(4L);
    }

    private static boolean isThreeOfKind() {
        return cardTypesCountersMap.containsValue(3L);
    }

    private static Integer calculateNumberOfPairs() {
        return (int) cardTypesCountersMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 2L)
                .count();
    }

    private static boolean isRoyalFlush() {
        return isFlush
                && isStraight
                && handCards.get(0).getCardType().equals(CardType.A)
                && handCards.get(1).getCardType().equals(CardType.K);
    }

    private static boolean isFlush() {
        return 1 == handCards
                .stream()
                .map(Card::getCardColor)
                .collect(Collectors.toSet())
                .size();
    }

    private static boolean isStraight() {
        //is it Straight from Ace
        if (handCards.get(0).getCardType().equals(CardType.A)
                && handCards.get(1).getCardType().equals(CardType.FIVE)
                && handCards.get(2).getCardType().equals(CardType.FOUR)
                && handCards.get(3).getCardType().equals(CardType.THREE)
                && handCards.get(4).getCardType().equals(CardType.TWO)) {
            return true;
        }
        //is it any other Straight
        int maxCardValue = handCards.get(0).getValue();
        for (int i = 1; i < handCards.size(); i++) {
            if (handCards.get(i).getValue() != (maxCardValue - i)) return false;
        }
        return true;
    }
}
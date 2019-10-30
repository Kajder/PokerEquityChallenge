package pokerequitychallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HandTypeCalculator {
    private HandType handType;
    private List<Card> handCards;
    private List<Card> sortedHandCards;
    private Map<CardType, Integer> cardTypesCountersMap;
    private boolean isRoyalFlush;
    private boolean isFlush;
    private boolean isStraight;
    private boolean isFourOfKind;
    private boolean isThreeOfKind;
    private boolean isFullHouse;
    private Integer numberOfPairs;


    public HandTypeCalculator(List<Card> handCards) {
        this.handCards = handCards;
        this.sortedHandCards = sortCardsByValue();
        this.handType = calculateHandType();
    }

    public HandType calculateHandType() {
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

    private void checkForCardTypesMultiplications() {
        this.cardTypesCountersMap = new HashMap<>();
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

    private boolean isFullHouse() {
        return cardTypesCountersMap.containsValue(3) && cardTypesCountersMap.containsValue(2);
    }

    private boolean isFourOfKind() {
        return cardTypesCountersMap.containsValue(4);
    }

    private boolean isThreeOfKind() {
        return cardTypesCountersMap.containsValue(3);
    }

    private Integer calculateNumberOfPairs() {
        Integer pairsCounter = 0;
        Collection<Integer> mapValues = cardTypesCountersMap.values();
        for (Integer value : mapValues) {
            if (value == 2) pairsCounter++;
        }
        return pairsCounter;
    }

    private boolean isRoyalFlush() {
        if (isFlush && isStraight
                && sortedHandCards.get(0).getCardType().equals(CardType.A)
                && sortedHandCards.get(1).getCardType().equals(CardType.K)) {
            return true;
        }
        return false;
    }

    private boolean isFlush() {
        CardColor cardColor = sortedHandCards.get(0).getCardColor();
        for (int i = 1; i < sortedHandCards.size(); i++) {
            if (!sortedHandCards.get(i).getCardColor().equals(cardColor)) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight() {
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

    private List<Card> sortCardsByValue() {
        List<Card> sortedHandCards = new ArrayList<>();
        List<Card> handCards = new ArrayList<>();
        handCards.addAll(this.handCards);

        while (handCards.size() != 0) {
            short maxCardValue = 0;

            for (Card card : handCards) {
                if (card.getValue() > maxCardValue) {
                    maxCardValue = card.getValue();
                }
            }

            Iterator<Card> iter = handCards.iterator();
            while (iter.hasNext()) {
                Card card = iter.next();
                if (card.getValue() == maxCardValue) {
                    sortedHandCards.add(card);
                    iter.remove();
                }
            }
        }
        //System.out.println(sortedHandCards);
        return sortedHandCards;
    }

    public List<Card> getSortedHandCards() {
        return sortedHandCards;
    }

    public HandType getHandType() {
        return handType;
    }

    public Map<CardType, Integer> getCardTypesCountersMap() {
        return cardTypesCountersMap;
    }

    public boolean getIsRoyalFlush() {
        return isRoyalFlush;
    }

    public boolean getIsFlush() {
        return isFlush;
    }

    public boolean getIsStraight() {
        return isStraight;
    }

    public boolean getIsFourOfKind() {
        return isFourOfKind;
    }

    public boolean getIsThreeOfKind() {
        return isThreeOfKind;
    }

    public boolean getIsFullHouse() {
        return isFullHouse;
    }

    public Integer getNumberOfPairs() {
        return numberOfPairs;
    }
}

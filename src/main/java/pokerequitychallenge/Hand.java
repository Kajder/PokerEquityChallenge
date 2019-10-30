package pokerequitychallenge;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private int playerId;
    private HandType handType;
    private List<Card> handCards;
    private HandTypeCalculator handTypeCalculator;

    Hand(List<Card> handCards) {
        this.handCards = handCards;
        this.handTypeCalculator = new HandTypeCalculator(this.handCards);
        this.handType = handTypeCalculator.getHandType();
    }

    public Hand(CardType type_1, CardColor color_1,
                CardType type_2, CardColor color_2,
                CardType type_3, CardColor color_3,
                CardType type_4, CardColor color_4,
                CardType type_5, CardColor color_5) {
        List<Card> handCards = new ArrayList<>();
        handCards.add(new Card(type_1, color_1));
        handCards.add(new Card(type_2, color_2));
        handCards.add(new Card(type_3, color_3));
        handCards.add(new Card(type_4, color_4));
        handCards.add(new Card(type_5, color_5));
        this.handCards = handCards;
        this.handTypeCalculator = new HandTypeCalculator(this.handCards);
        this.handType = handTypeCalculator.getHandType();


    }

    public Hand(List<Card> handCards, int playerId) {
        this.handCards = handCards;
        HandTypeCalculator handTypeCalculator = new HandTypeCalculator(handCards);
        this.handType = handTypeCalculator.getHandType();
        this.playerId = playerId;
    }

    public void printHand(){
        System.out.println("//// Hand " + this.hashCode());
        handCards.forEach(card -> System.out.println(card.toString()));
        System.out.println("////");
    }

    HandType getHandType() {
        return handType;
    }

    HandTypeCalculator getHandTypeCalculator() {
        return handTypeCalculator;
    }

    List<Card> getHandCards() {
        return handCards;
    }

    short getCardValue(int sortedCardIndex){
        return handTypeCalculator.getSortedHandCards().get(sortedCardIndex).getValue();
    }

}
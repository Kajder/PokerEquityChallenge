package pokerequitychallenge;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class Hand implements Comparable<Hand> {

    private int playerId;
    private HandType handType;
    private List<Card> handCards;
    private List<Card> handCardsSorted;

    public Hand(List<Card> handCards) {
        this.handCards = new LinkedList<>(handCards);
        this.handType = HandTypeCalculator.calculateHandType(handCards);
        this.handCardsSorted = handCards.stream().sorted().collect(Collectors.toList());
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
        this.handType = HandTypeCalculator.calculateHandType(handCards);
        this.handCardsSorted = handCards.stream().sorted().collect(Collectors.toList());
    }

    short getCardValue(int sortedCardIndex) {
        return handCardsSorted.get(sortedCardIndex).getValue();
    }

    @Override
    public int compareTo(Hand o) {
        return this.getHandType().ordinal() - o.getHandType().ordinal();
    }

    @Override
    public String toString() {
        return handType.toString() + ": " + handCards.stream().map(Objects::toString).collect(Collectors.joining(", "));
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
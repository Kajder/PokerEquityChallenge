package pokerequitychallenge;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Getter
public class Hand implements Comparable<Hand> {

    private final List<Card> handCards;
    private final HandType handType;
    private int playerId;
    private int nonFigureCardsValue;
    private int handCardsValuesSum;
    private Map<CardType, Long> handCardsTypesOccurrences;

    public Hand(List<Card> handCards) {
        this.handCards = handCards.stream().sorted().collect(Collectors.toList());
        this.handType = HandTypeCalculator.calculateHandType(handCards);
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
        this.handCards = handCards.stream().sorted().collect(Collectors.toList());
        this.handType = HandTypeCalculator.calculateHandType(handCards);
        setUtils();
    }

    public void setUtils() {
        handCardsValuesSum = handCards.stream().map(Card::getValue).reduce(0, Integer::sum);
        handCardsTypesOccurrences = handCards.stream().collect(groupingBy(Card::getCardType, Collectors.counting()));
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setNonFigureCardsValue(int value) {
        this.nonFigureCardsValue = value;
    }

    @Override
    public int compareTo(Hand o) {
        return this.getHandType().ordinal() - o.getHandType().ordinal();
    }

    @Override
    public String toString() {
        return handType.toString() + ": " + handCards.stream().map(Objects::toString).collect(Collectors.joining(", "));
    }
}
package pokerequitychallenge;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card implements Comparable<Card> {

    private final CardType cardType;
    private final CardColor cardColor;

    public int getValue() {
        return this.cardType.value;
    }

    @Override
    public String toString() {
        return cardType.toString() + "-" + cardColor.toString();
    }

    @Override
    public int compareTo(Card o) {
        return o.getCardType().value - this.getCardType().value;
    }
}

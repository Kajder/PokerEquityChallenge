package pokerequitychallenge.card;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Card card = (Card) obj;

        return (card.cardType.ordinal() == this.cardType.ordinal())
                && (card.cardColor.ordinal() == this.cardColor.ordinal());
    }

    @Override
    public int hashCode() {
        return (cardType.ordinal() + 1) * (cardColor.ordinal() + 1);
    }
}

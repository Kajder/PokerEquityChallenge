package pokerequitychallenge;

public class Card {

    private CardType cardType;
    private CardColor cardColor;
    private short value;

    public Card(CardType cardType, CardColor cardColor) {
        this.cardType = cardType;
        this.cardColor = cardColor;
        this.value = CardValueSetter.assignValue(cardType);
    }

    public CardType getCardType() {
        return cardType;
    }

    CardColor getCardColor() {
        return cardColor;
    }

    short getValue() {
        return value;
    }

    @Override
    public String toString() {
        return cardType.toString() + "-" + cardColor.toString();
    }
}

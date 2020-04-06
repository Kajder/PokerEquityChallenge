package pokerequitychallenge;

public enum CardType {

    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(11),
    Q(12),
    K(13),
    A(14);

    public final short value;

    CardType(int value) {
        this.value = (short) value;
    }

}

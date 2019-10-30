package pokerequitychallenge;

public class CardValueSetter {

    public static short assignValue (CardType cardType){
        short value = 0;

        switch (cardType) {
            case TWO:
                value = 2;
                break;
            case THREE:
                value = 3;
                break;
            case FOUR:
                value = 4;
                break;
            case FIVE:
                value = 5;
                break;
            case SIX:
                value = 6;
                break;
            case SEVEN:
                value = 7;
                break;
            case EIGHT:
                value = 8;
                break;
            case NINE:
                value = 9;
                break;
            case TEN:
                value = 10;
                break;
            case J:
                value = 11;
                break;
            case Q:
                value = 12;
                break;
            case K:
                value = 13;
                break;
            case A:
                value = 14;
                break;
        }

        return value;
    }
}

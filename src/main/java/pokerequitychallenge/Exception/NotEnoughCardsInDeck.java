package pokerequitychallenge.Exception;

public class NotEnoughCardsInDeck extends RuntimeException {

    public NotEnoughCardsInDeck() {
        super("Drawing not possible - not enough cards left in deck");
    }
}

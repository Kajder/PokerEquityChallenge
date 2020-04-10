package pokerequitychallenge.Exception;

public class CardNotFound extends RuntimeException {

    public CardNotFound() {
        super("Card could not be found");
    }
}

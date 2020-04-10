package pokerequitychallenge.Exception;

public class HandNotFound extends RuntimeException {

    public HandNotFound() {
        super("Hand could not be found");
    }
}

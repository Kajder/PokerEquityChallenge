package pokerequitychallenge.Exception;

public class FlopSizeExceeded extends RuntimeException {

    public FlopSizeExceeded() {
        super("Maximum allowed number of cards to be drawn is 3");
    }
}

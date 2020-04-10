package pokerequitychallenge.Exception;

public class RiverSizeExceeded extends RuntimeException {

    public RiverSizeExceeded() {
        super("Maximum allowed number of cards to be drawn is 5");
    }
}

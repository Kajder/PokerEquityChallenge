package pokerequitychallenge;

import lombok.Getter;
import pokerequitychallenge.Exception.NotEnoughCardsInDeck;
import pokerequitychallenge.Exception.RiverSizeExceeded;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class CardDrawer {

    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    List<Card> currentDeck;

    public CardDrawer() {
        this.currentDeck = new LinkedList<>(CardDeck.deck);
    }

    public void rebuildDeckExcluding(List<Card> cardsToExclude) {
        List<Card> deck = new LinkedList<>(CardDeck.deck);
        deck.removeAll(cardsToExclude);
        this.currentDeck = deck;
    }

    public List<Card> drawCards(int n) {
        if (n > 5) throw new RiverSizeExceeded();

        List<Card> cards = new LinkedList<>();
        for (int i = 0; i < n; i++) cards.add(drawCard());
        return cards;
    }

    public Card drawCard() {
        if (currentDeck.isEmpty()) throw new NotEnoughCardsInDeck();
        int randomIndex = random.nextInt(currentDeck.size());
        Card card = this.currentDeck.get(randomIndex);
        currentDeck.remove(randomIndex);
        return card;
    }
}

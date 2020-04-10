package pokerequitychallenge;

import lombok.Getter;
import pokerequitychallenge.Exception.NotEnoughCardsInDeck;
import pokerequitychallenge.Exception.RiverSizeExceeded;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class CardDrawer {

    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    List<Card> currentDeck;

    public CardDrawer() {
        this.currentDeck = new ArrayList<>(CardDeck.deck);
    }

    public void rebuildRandomDeckExcluding(List<Card> cardsToExclude) {
        List<Card> deck = new ArrayList<>(CardDeck.deck);
        deck.removeAll(cardsToExclude);
        this.currentDeck = deck;
    }

    public List<Card> drawCards(int n) {
//        if (n > 5) throw new RiverSizeExceeded();

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

    public List<Card> drawFlop() {
        return drawCards(3);
    }

    public List<Card> drawTurn(List<Card> flop) {
        flop.addAll(drawCards(1));
        return flop;
    }

    public List<Card> drawRiver(List<Card> turn) {
        turn.addAll(drawCards(1));
        return turn;
    }

    public List<Card> drawTwoIndividualCards() {
        return drawCards(2);
    }
}

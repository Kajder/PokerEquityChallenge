package pokerequitychallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDrawer {

    CardDeck cardDeck;
    List<Card> currentDeck;


    public CardDrawer() {
        this.cardDeck = new CardDeck();
        this.currentDeck = drawCurrentDeck();
    }

    private List<Card> drawCurrentDeck() {
        List<Card> currentDeck = new ArrayList<>();
        for (Card card : cardDeck.getDeck()) {
            currentDeck.add(card);
        }
        Collections.shuffle(currentDeck);
        return currentDeck;
    }

    public List<Card> drawFlop() {
        List<Card> flop = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            flop.add(this.currentDeck.get(0));
            currentDeck.remove(0);
        }

        return flop;
    }

    public List<Card> drawTurn(List<Card> flop) {
        List<Card> turn = new ArrayList<>();
        turn.addAll(flop);
        turn.add(this.currentDeck.get(0));
        currentDeck.remove(0);

        return turn;
    }

    public List<Card> drawRiver(List<Card> turn) {
        List<Card> river = new ArrayList<>();
        river.addAll(turn);
        river.add(this.currentDeck.get(0));
        currentDeck.remove(0);

        return river;
    }

    public List<Card> drawTwoIndividualCards() {
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            hand.add(this.currentDeck.get(0));
            currentDeck.remove(0);
        }

        return hand;
    }

    public List<Card> getCurrentDeck() {
        return currentDeck;
    }
}

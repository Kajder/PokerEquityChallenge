package pokerequitychallenge;

import lombok.Getter;
import pokerequitychallenge.Exception.FlopSizeExceeded;
import pokerequitychallenge.Exception.NotEnoughCardsInDeck;

import java.util.LinkedList;
import java.util.List;

@Getter
public class CardDrawer {

    List<Card> currentDeck;

    public CardDrawer() {
        this.currentDeck = CardDeck.shuffleDeck();
    }

    private List<Card> drawCards(int n) {
        if (n > 3) throw new FlopSizeExceeded();
        if (currentDeck.size() < n) throw new NotEnoughCardsInDeck();

        List<Card> cards = new LinkedList<>();
        for (int i = 0; i < n; i++) cards.add(this.currentDeck.get(i));
        currentDeck.removeAll(cards);
        return cards;
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

package pokerequitychallenge.player;

import lombok.Getter;
import pokerequitychallenge.card.Card;
import pokerequitychallenge.hand.Hand;
import pokerequitychallenge.hand.HandsComparator;

import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Getter
public class Player {

    private static final int AVAILABLE_CARDS_NUMBER = 7;
    private final int id;
    private final List<Card> pair;
    private Hand highestHand;

    public Player(int id, List<Card> pair) {
        this.id = id;
        this.pair = pair;
    }

    public void calculateHighestHand(@Size(min = 5, max = 5) List<Card> river) {
        List<Card> pool = new LinkedList<>(river);
        pool.addAll(pair);

        List<Hand> possibleHands = new LinkedList<>();
        List<Card> handCards = new LinkedList<>();
        for (int i = 0; i < AVAILABLE_CARDS_NUMBER - 1; i++) {
            for (int j = i + 1; j < AVAILABLE_CARDS_NUMBER; j++) {
                handCards.addAll(pool);
                Card firstCardToRemove = handCards.get(i);
                Card secondCardToRemove = handCards.get(j);
                handCards.remove(firstCardToRemove);
                handCards.remove(secondCardToRemove);

                possibleHands.add(new Hand(handCards));

                handCards.clear();
            }
        }
        highestHand = HandsComparator.getWinningHands(possibleHands).get(0);
        highestHand.setPlayerId(id);
    }
}

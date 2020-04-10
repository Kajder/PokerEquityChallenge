package pokerequitychallenge;

import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Getter
public class Player {

    List<Card> pair;
    Hand highestHand;
    int id;

    public Player(int id, List<Card> pair) {
        this.id = id;
        this.pair = pair;
    }

    public void calculateHighestHand(@Size(min = 5, max = 5) List<Card> river) {
        List<Card> pool = new LinkedList<>(river);
        pool.addAll(pair);

        List<Hand> possibleHands = new LinkedList<>();
        List<Card> handCards = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 7; j++) {
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

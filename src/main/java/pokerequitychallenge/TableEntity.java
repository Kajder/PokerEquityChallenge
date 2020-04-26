package pokerequitychallenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class TableEntity {

    List<Card> board = new LinkedList<>();
    List<PlayerHand> playerHands = new LinkedList<>();

    public TableEntity(EquityRequest equityRequest) {
        for (Card card : equityRequest.getBoard()) {
            this.board.add(
                    new Card(card.getCardType(), card.getCardColor()));
        }

        for (PlayerHand playerHand : equityRequest.getPlayerHands()) {
            this.playerHands.add(
                    new PlayerHand(
                            playerHand.playerId,
                            playerHand.playerCards.stream()
                                    .map(c -> new Card(c.getCardType(), c.getCardColor()))
                                    .collect(Collectors.toList())));
        }
    }

}

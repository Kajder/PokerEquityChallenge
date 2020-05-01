package pokerequitychallenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pokerequitychallenge.card.Card;
import pokerequitychallenge.player.PlayerHand;
import pokerequitychallenge.request.EquityRequest;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class Table {

    List<Card> board = new LinkedList<>();
    List<PlayerHand> playerHands = new LinkedList<>();

    public Table(EquityRequest equityRequest) {
        for (Card card : equityRequest.getBoard()) {
            this.board.add(
                    new Card(card.getCardType(), card.getCardColor()));
        }

        for (PlayerHand playerHand : equityRequest.getPlayerHands()) {
            this.playerHands.add(
                    new PlayerHand(
                            playerHand.getPlayerId(),
                            playerHand.getPlayerCards().stream()
                                    .map(c -> new Card(c.getCardType(), c.getCardColor()))
                                    .collect(Collectors.toList())));
        }
    }

}

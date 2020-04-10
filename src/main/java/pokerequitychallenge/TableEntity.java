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

    List<Card> river = new LinkedList<>();
    List<PlayerHand> playerHands = new LinkedList<>();

    TableEntity(EquityRequest equityRequest) {
        for (Card card : equityRequest.river) {
            this.river.add(
                    new Card(card.getCardType(), card.getCardColor()));
        }

        for (PlayerHand playerHand : equityRequest.playerHands) {
            this.playerHands.add(
                    new PlayerHand(
                            playerHand.playerId,
                            playerHand.playerCards.stream()
                                    .map(c -> new Card(c.getCardType(), c.getCardColor()))
                                    .collect(Collectors.toList())));
        }
    }

}

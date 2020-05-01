package pokerequitychallenge.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pokerequitychallenge.card.Card;
import pokerequitychallenge.player.PlayerHand;

import javax.validation.constraints.Size;
import java.util.List;

//todo własny walidator do unikatowych kart
@AllArgsConstructor
@Getter
public class EquityRequest {

    @Size(max = 5)
    private final List<Card> board;

    @Size(min = 2, max = 10)
    private final List<PlayerHand> playerHands;
}

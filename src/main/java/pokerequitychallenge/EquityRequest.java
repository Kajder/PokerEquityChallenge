package pokerequitychallenge;

import lombok.AllArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

//todo własny walidator do unikatowych kart
@AllArgsConstructor
public class EquityRequest {

    @Size(max = 5)
    List<Card> river;

    @Size(min = 2, max = 10)
    List<PlayerHand> playerHands;
}

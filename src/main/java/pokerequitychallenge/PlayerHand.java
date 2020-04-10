package pokerequitychallenge;

import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
public class PlayerHand {

    @NotNull
    int playerId;

    @Size(max = 2)
    List<Card> playerCards;
}

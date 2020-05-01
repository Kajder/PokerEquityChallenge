package pokerequitychallenge.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pokerequitychallenge.card.Card;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Getter
public class PlayerHand {

    @NotNull
    int playerId;

    @Size(max = 2)
    List<Card> playerCards;
}

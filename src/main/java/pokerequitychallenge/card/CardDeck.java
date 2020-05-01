package pokerequitychallenge.card;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class CardDeck {

    public static final Set<Card> deck = new HashSet<>();

    static {
        List.of(CardType.class.getEnumConstants()).forEach(cardType ->
                List.of(CardColor.class.getEnumConstants()).forEach(cardColor ->
                        deck.add(new Card(cardType, cardColor))));
    }
}

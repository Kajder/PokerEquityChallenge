package pokerequitychallenge;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CardDeck {

    public static final List<Card> deck = new ArrayList<>();

    static {
        List.of(CardType.class.getEnumConstants()).forEach(cardType ->
                List.of(CardColor.class.getEnumConstants()).forEach(cardColor ->
                        deck.add(new Card(cardType, cardColor))));
    }
}

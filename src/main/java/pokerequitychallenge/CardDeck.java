package pokerequitychallenge;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Getter
public class CardDeck {
    private static List<Card> deck = new LinkedList<>();

    static {
        Arrays.asList(CardType.class.getEnumConstants()).forEach(cardType ->
                Arrays.asList(CardColor.class.getEnumConstants()).forEach(cardColor ->
                        deck.add(new Card(cardType, cardColor))));
    }

    public static List<Card> shuffleDeck() {
        Collections.shuffle(deck);
        return deck;
    }
}

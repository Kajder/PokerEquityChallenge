package pokerequitychallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardDeck {
    private List<CardType> cardTypes;
    private List<CardColor> cardColors;
    private Set<Card> deck = new HashSet<>();

    public CardDeck() {
        cardTypes = Arrays.asList(CardType.class.getEnumConstants());
        cardColors = Arrays.asList(CardColor.class.getEnumConstants());
        for (CardType cardType : cardTypes) {
            for (CardColor cardColor : cardColors) {
                deck.add(new Card(cardType, cardColor));
            }
        }


        System.out.println(Arrays.asList(HandType.class.getEnumConstants()));
    }

    public Set<Card> getDeck() {
        return deck;
    }
}

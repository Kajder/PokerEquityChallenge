package pokerequitychallenge;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<Card> flop;
    private List<Card> turn;
    private List<Card> river;
    private List<Card> twoIndividualPlayer1;
    private List<Card> twoIndividualPlayer2;
    private List<Card> handCardsPlayer1;
    private List<Card> handCardsPlayer2;
    private Hand handPlayer1;
    private Hand handPlayer2;

    public Table() {
        CardDrawer cardDrawer = new CardDrawer();
        System.out.println(cardDrawer.getCurrentDeck().size() + ":" + cardDrawer.getCurrentDeck());

        this.flop = cardDrawer.drawFlop();
        System.out.println(flop.size() + ":" + flop);

        this.turn = cardDrawer.drawTurn(flop);
        System.out.println(turn.size() + ":" + turn);

        this.river = cardDrawer.drawRiver(turn);
        System.out.println(river.size() + ":" + river);

        this.twoIndividualPlayer1 = cardDrawer.drawTwoIndividualCards();
        System.out.println(twoIndividualPlayer1.size() + ":" + twoIndividualPlayer1);

        this.twoIndividualPlayer2 = cardDrawer.drawTwoIndividualCards();
        System.out.println(twoIndividualPlayer2.size() + ":" + twoIndividualPlayer2);


        System.out.println(cardDrawer.getCurrentDeck().size() + ":" + cardDrawer.getCurrentDeck());

        System.out.println("\nHAND TYPES:\n\n");
        handCardsPlayer1 = new ArrayList<Card>();
        handCardsPlayer1.addAll(river);
        handCardsPlayer1.addAll(twoIndividualPlayer1);
        handPlayer1 = new Hand(handCardsPlayer1);
        System.out.println(handCardsPlayer1);
        System.out.println("hand type: " + handPlayer1.getHandType());


    }
}

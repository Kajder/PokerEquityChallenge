package pokerequitychallenge;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<Card> flop, turn, river, twoIndividualPlayer1, twoIndividualPlayer2, handCardsPlayer1, handCardsPlayer2;
    private Hand handPlayer1, handPlayer2;

    public Table() {
        CardDrawer cardDrawer = new CardDrawer();
        System.out.println("deck: " + cardDrawer.getCurrentDeck().size() + ":" + cardDrawer.getCurrentDeck());

        this.flop = cardDrawer.drawFlop();
        System.out.println("flop: " + flop.size() + ":" + flop);

        this.turn = cardDrawer.drawTurn(flop);
        System.out.println("turn: " + turn.size() + ":" + turn);

        this.river = cardDrawer.drawRiver(turn);
        System.out.println("river " + river.size() + ":" + river);

        this.twoIndividualPlayer1 = cardDrawer.drawTwoIndividualCards();
        System.out.println("player 1: " + twoIndividualPlayer1.size() + ":" + twoIndividualPlayer1);

        this.twoIndividualPlayer2 = cardDrawer.drawTwoIndividualCards();
        System.out.println("player 2: " + twoIndividualPlayer2.size() + ":" + twoIndividualPlayer2);


        System.out.println("deck: " + cardDrawer.getCurrentDeck().size() + ":" + cardDrawer.getCurrentDeck());

        System.out.println("\nHAND TYPES:\n\n");
        System.out.println("hand type: " + new Hand(river).getHandType());
        System.out.println("hand sorted: " + new Hand(river).getHandCardsSorted());

    }
}

package pokerequitychallenge;

import pokerequitychallenge.card.Card;
import pokerequitychallenge.card.CardDrawer;
import pokerequitychallenge.hand.Hand;
import pokerequitychallenge.hand.HandsComparator;
import pokerequitychallenge.player.Player;
import pokerequitychallenge.player.PlayerHand;
import pokerequitychallenge.request.EquityRequest;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    public Game() {
        long start = System.currentTimeMillis();
        int gamesNumber = 10000;
        EquityRequest request = createEquityRequest();

        List<Card> requestCardsList = findAllCardsFromRequest(request);

//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            String json = mapper.writeValueAsString(request);
//            System.out.println("JSON = " + json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }


        Map<Integer, Float> scores = request.getPlayerHands().stream().collect(
                Collectors.toMap(PlayerHand::getPlayerId, playerHand -> 0F));

        CardDrawer cardDrawer = new CardDrawer();

        for (int i = 0; i < gamesNumber; i++) {
            Table table = new Table(request);
            cardDrawer.buildDeckExcluding(requestCardsList);

            drawAndDealMissingCards(table, cardDrawer);

            List<Hand> winningHands = HandsComparator.getWinningHands(
                    table.playerHands.stream()
                            .map(playerHand -> new Player(playerHand.getPlayerId(), playerHand.getPlayerCards()))
                            .peek(player -> player.calculateHighestHand(table.board))
                            .map(Player::getHighestHand)
                            .collect(Collectors.toList())
            );
            winningHands.forEach(hand -> scores.put(
                    hand.getPlayerId(),
                    scores.get(hand.getPlayerId()) + 1F));
        }

        scores.forEach((k, v) -> scores.put(k, (scores.get(k) / gamesNumber)));

        scores.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println(scores.values().stream().reduce(0F, Float::sum));

        System.out.println("duration: ");
        System.out.println(System.currentTimeMillis() - start);
    }

    private List<Card> findAllCardsFromRequest(EquityRequest request) {
        return Stream.concat(
                request.getBoard().stream(),
                request.getPlayerHands().stream()
                        .map(PlayerHand::getPlayerCards)
                        .flatMap(Collection::stream))
                .collect(Collectors.toList());
    }

    private EquityRequest createEquityRequest() {
        CardDrawer tempDrawer = new CardDrawer();
        return new EquityRequest(
                tempDrawer.drawCards(0),
                List.of(
                        new PlayerHand(1, tempDrawer.drawCards(0)),
                        new PlayerHand(2, tempDrawer.drawCards(0)),
                        new PlayerHand(3, tempDrawer.drawCards(0)),
                        new PlayerHand(4, tempDrawer.drawCards(0)),
                        new PlayerHand(5, tempDrawer.drawCards(0)),
                        new PlayerHand(6, tempDrawer.drawCards(0)),
                        new PlayerHand(7, tempDrawer.drawCards(0)),
                        new PlayerHand(8, tempDrawer.drawCards(0)),
                        new PlayerHand(9, tempDrawer.drawCards(0)),
                        new PlayerHand(10, tempDrawer.drawCards(0)))
        );

//        return new EquityRequest(
//                List.of(
//                        new Card(CardType.A, CardColor.DIAMOND),
//                        new Card(CardType.A, CardColor.HEART),
//                        new Card(CardType.A, CardColor.SPADE),
//                        new Card(CardType.TEN, CardColor.CLUB),
//                        new Card(CardType.K, CardColor.CLUB)
//                ),
//                List.of(
//                        new PlayerHand(1, List.of(new Card(CardType.TWO, CardColor.CLUB), new Card(CardType.THREE, CardColor.DIAMOND))),
//                        new PlayerHand(2, List.of(new Card(CardType.TEN, CardColor.DIAMOND), new Card(CardType.SEVEN, CardColor.DIAMOND))),
//                        new PlayerHand(3, List.of(new Card(CardType.FIVE, CardColor.CLUB), new Card(CardType.SIX, CardColor.SPADE)))
//                )
//        );
    }

    private void drawAndDealMissingCards(Table table, CardDrawer cardDrawer) {
        table.board.addAll(cardDrawer.drawCards((5 - table.board.size())));
        table.playerHands.forEach(p -> {
            if (p.getPlayerCards().size() < 2)
                p.getPlayerCards().addAll(cardDrawer.drawCards(2 - p.getPlayerCards().size()));
        });
    }
}


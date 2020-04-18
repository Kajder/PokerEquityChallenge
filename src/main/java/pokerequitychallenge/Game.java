package pokerequitychallenge;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    public Game() {
        int gamesNumber = 10000;
        EquityRequest request = createEquityRequest();

        List<Card> requestCardsList = findAllCardsFromRequest(request);
        Map<Integer, Float> scores = request.playerHands.stream().collect(
                Collectors.toMap(PlayerHand::getPlayerId, playerHand -> 0F));

        CardDrawer cardDrawer = new CardDrawer();

        for (int i = 0; i < gamesNumber; i++) {
            TableEntity table = new TableEntity(request);
            cardDrawer.rebuildDeckExcluding(requestCardsList);

            drawAndDealMissingCards(table, cardDrawer);

            List<Hand> winningHands = HandsComparator.getWinningHands(
                    table.playerHands.stream()
                            .map(playerHand -> new Player(playerHand.playerId, playerHand.playerCards))
                            .peek(player -> player.calculateHighestHand(table.river))
                            .map(player -> player.highestHand)
                            .collect(Collectors.toList())
            );
            winningHands.forEach(hand -> scores.put(
                    hand.getPlayerId(),
                    scores.get(hand.getPlayerId()) + 1F));
        }

        scores.forEach((k, v) -> scores.put(k, (scores.get(k) / gamesNumber)));
        EquityResponse response = new EquityResponse(scores);

        response.equities.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println(response.equities.values().stream().reduce(0F, Float::sum));
    }

    private List<Card> findAllCardsFromRequest(EquityRequest request) {
        return Stream.concat(
                request.river.stream(),
                request.playerHands.stream()
                        .map(playerHand -> playerHand.playerCards)
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
//                        new Card(CardType.SIX, CardColor.CLUB),
//                        new Card(CardType.K, CardColor.CLUB),
//                        new Card(CardType.J, CardColor.CLUB)
//                ),
//                List.of(
//                        new PlayerHand(1, List.of(new Card(CardType.A, CardColor.CLUB), new Card(CardType.A, CardColor.DIAMOND))),
//                        new PlayerHand(2, List.of(new Card(CardType.TEN, CardColor.DIAMOND), new Card(CardType.SEVEN, CardColor.DIAMOND))),
//                        new PlayerHand(3, List.of(new Card(CardType.TWO, CardColor.CLUB), new Card(CardType.TEN, CardColor.SPADE)))
//                )
//        );
    }

    private void drawAndDealMissingCards(TableEntity table, CardDrawer cardDrawer) {
        table.river.addAll(cardDrawer.drawCards((5 - table.river.size())));
        table.playerHands.forEach(p -> {
            if (p.playerCards.size() < 2)
                p.playerCards.addAll(cardDrawer.drawCards(2 - p.playerCards.size()));
        });
    }
}
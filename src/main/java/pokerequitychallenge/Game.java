package pokerequitychallenge;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    private List<Card> flop, turn, river, twoIndividualPlayer1, twoIndividualPlayer2;
    private List<Hand> winningHands;
    private CardDrawer cardDrawer;

    public Game() {
//        runTwoPlayersGames(10000);

        int gamesNumber = 1000;
        EquityRequest request = createEquityRequest();

        List<Card> requestCardsList = findAllCardsFromRequest(request);
        Map<Integer, Float> scores = new HashMap<>(); //TODO Java11 groupingBy
        request.playerHands.forEach(playerHand -> scores.put(playerHand.playerId, 0F));

        //utwórz globalny drawer dla wszystkich gier
        CardDrawer cardDrawer = new CardDrawer();

        for (int i = 0; i < gamesNumber; i++) {
            cardDrawer.rebuildRandomDeckExcluding(requestCardsList);
            //przejdź po wszystkich kolekcjach z requesta i rozlosuj im brakujące karty
            TableEntity table = new TableEntity(request);
            table.river.addAll(cardDrawer.drawCards((5 - table.river.size())));

            for (PlayerHand playerHand : table.playerHands) {
                if (playerHand.playerCards.size() == 1) {
                    playerHand.playerCards.add(cardDrawer.drawCard());
                } else if (playerHand.playerCards.size() == 0) {
                    playerHand.playerCards.addAll(cardDrawer.drawCards(2));
                }
            }

            //znajdź zwycięzców:
            //dla każdego gracza znajduję zwycięską rękę, a następnie spośród zwycięskich wyszukuję najlepszą (najlepsze jeśli remis)
            List<Hand> winningHands = HandsComparator.getWinningHands(
                    table.playerHands.stream()
                            .map(playerHand -> new Player(playerHand.playerId, playerHand.playerCards))
                            .peek(player -> player.calculateHighestHand(table.river))
                            .map(player -> player.highestHand)
                            .collect(Collectors.toList())
            );
            List<Integer> winningPlayersIds = winningHands.stream().map(Hand::getPlayerId).collect(Collectors.toList());
            winningPlayersIds.forEach(id -> scores.put(id, scores.get(id) + 1F));
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
        //tymczasowy drawer tylko po to, żebym nie musiał wpisywać kart ręcznie i żeby były losowe
        CardDrawer tempDrawer = new CardDrawer();
        return new EquityRequest(
                tempDrawer.drawCards(3),
                List.of(
                        new PlayerHand(1, tempDrawer.drawCards(2)),
//                        new PlayerHand(2, tempDrawer.drawCards(0)),
//                        new PlayerHand(3, tempDrawer.drawCards(0)),
//                        new PlayerHand(4, tempDrawer.drawCards(0)),
//                        new PlayerHand(5, tempDrawer.drawCards(0)),
//                        new PlayerHand(6, tempDrawer.drawCards(0)),
//                        new PlayerHand(7, tempDrawer.drawCards(0)),
//                        new PlayerHand(8, tempDrawer.drawCards(0)),
//                        new PlayerHand(9, tempDrawer.drawCards(0)),
                        new PlayerHand(10, tempDrawer.drawCards(2)))
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

    private void runTwoPlayersGames(int numberOfGames) {
        long globalStart = System.currentTimeMillis();
        for (int i = 0; i < numberOfGames; i++) {
            runSingleGame(i);
        }
        long globalDuration = (System.currentTimeMillis() - globalStart);
        System.out.println("End of all games: " + numberOfGames + " [" + globalDuration + "]");
        System.out.println("Avg time: " + (globalDuration / numberOfGames));
    }

    private void runSingleGame(int i) {
        System.out.println("Start of game no: " + (i + 1));
        long start = System.currentTimeMillis();

        cardDrawer = new CardDrawer();

        this.flop = cardDrawer.drawFlop();
        this.turn = cardDrawer.drawTurn(flop);
        this.river = cardDrawer.drawRiver(turn);
        System.out.println("river " + river.size() + ":" + river);

        this.twoIndividualPlayer1 = cardDrawer.drawTwoIndividualCards();
        System.out.println("player 1: " + twoIndividualPlayer1.size() + ":" + twoIndividualPlayer1);

        this.twoIndividualPlayer2 = cardDrawer.drawTwoIndividualCards();
        System.out.println("player 2: " + twoIndividualPlayer2.size() + ":" + twoIndividualPlayer2);

        Player player_1 = new Player(1, twoIndividualPlayer1);
        player_1.calculateHighestHand(river);
        System.out.println("player 1 highest hand: " + player_1.highestHand.toString());

        Player player_2 = new Player(2, twoIndividualPlayer2);
        player_2.calculateHighestHand(river);
        System.out.println("player 2 highest hand: " + player_2.highestHand.toString());

        winningHands = HandsComparator.getWinningHands(List.of(player_1.highestHand, player_2.highestHand));

        System.out.println("Winner: " + winningHands.stream().map(Hand::getPlayerId).map(Objects::toString).collect(Collectors.joining(", ")));
        System.out.println("End of game no: " + (i + 1) + " [" + (System.currentTimeMillis() - start) + "]");
    }
}

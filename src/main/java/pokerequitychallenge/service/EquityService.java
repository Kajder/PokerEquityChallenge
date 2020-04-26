package pokerequitychallenge.service;

import org.springframework.stereotype.Service;
import pokerequitychallenge.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EquityService {

    //todo do yaml'a
    private final int gamesNumber = 10000;

    public Map<Integer, Float> calculateEquity(EquityRequest equityRequest) {
        List<Card> requestCardsList = findAllCardsFromRequest(equityRequest);

        Map<Integer, Float> scores = equityRequest.getPlayerHands().stream().collect(
                Collectors.toConcurrentMap(PlayerHand::getPlayerId, playerHand -> 0F));

        CardDrawer cardDrawer;

        for (int i = 0; i < gamesNumber; i++) {
            TableEntity table = new TableEntity(equityRequest);
            cardDrawer = new CardDrawer();

            cardDrawer.rebuildDeckExcluding(requestCardsList);

            drawAndDealMissingCards(table, cardDrawer);

            List<Hand> winningHands = HandsComparator.getWinningHands(
                    table.getPlayerHands().stream()
                            .map(playerHand -> new Player(playerHand.getPlayerId(), playerHand.getPlayerCards()))
                            .peek(player -> player.calculateHighestHand(table.getBoard()))
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
        return scores;
    }

    private Map<Integer, Float> runSingleEquityCalculation(Map<Integer, Float> scores,
                                                           EquityRequest equityRequest,
                                                           List<Card> requestCardsList) {
        TableEntity table = new TableEntity(equityRequest);
        CardDrawer cardDrawer = new CardDrawer();

        cardDrawer.rebuildDeckExcluding(requestCardsList);

        drawAndDealMissingCards(table, cardDrawer);

        List<Hand> winningHands = HandsComparator.getWinningHands(
                table.getPlayerHands().stream()
                        .map(playerHand -> new Player(playerHand.getPlayerId(), playerHand.getPlayerCards()))
                        .peek(player -> player.calculateHighestHand(table.getBoard()))
                        .map(Player::getHighestHand)
                        .collect(Collectors.toList())
        );
        winningHands.forEach(hand -> scores.put(
                hand.getPlayerId(),
                scores.get(hand.getPlayerId()) + 1F));

        return scores;
    }

    private List<Card> findAllCardsFromRequest(EquityRequest equityRequest) {
        return Stream.concat(
                equityRequest.getBoard().stream(),
                equityRequest.getPlayerHands().stream()
                        .map(PlayerHand::getPlayerCards)
                        .flatMap(Collection::stream))
                .collect(Collectors.toList());
    }

    private void drawAndDealMissingCards(TableEntity table, CardDrawer cardDrawer) {
        table.getBoard().addAll(cardDrawer.drawCards((5 - table.getBoard().size())));
        table.getPlayerHands().forEach(p -> {
            if (p.getPlayerCards().size() < 2)
                p.getPlayerCards().addAll(cardDrawer.drawCards(2 - p.getPlayerCards().size()));
        });
    }

}

package pokerequitychallenge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pokerequitychallenge.Table;
import pokerequitychallenge.card.Card;
import pokerequitychallenge.card.CardDrawer;
import pokerequitychallenge.player.PlayerHand;
import pokerequitychallenge.request.EquityRequest;
import pokerequitychallenge.utils.EquityCalculator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProbabilisticCalculationEquityService {

    @Value("${calculation.probability.games-number: 10000}")
    private int gamesNumber;

    public Map<Integer, Float> runProbabilisticCalculation(EquityRequest equityRequest,
                                                           List<Card> requestCardsList) {

        log.info("Probabilistic calculation has been started");

        Map<Integer, Float> scores = equityRequest.getPlayerHands().stream().collect(
                Collectors.toMap(PlayerHand::getPlayerId, playerHand -> 0F));

        CardDrawer cardDrawer;

        for (int i = 0; i < gamesNumber; i++) {
            Table table = new Table(equityRequest);
            cardDrawer = new CardDrawer();

            cardDrawer.buildDeckExcluding(requestCardsList);

            drawAndDealMissingCards(table, cardDrawer);

            EquityCalculator.findWinnersAndWriteScores(table, scores);
        }

        return EquityCalculator.convertScoresNumbersToPercentages(scores, gamesNumber);
    }

    private void drawAndDealMissingCards(Table table, CardDrawer cardDrawer) {
        table.getBoard().addAll(
                cardDrawer.drawCards(AllVariantsCalculationEquityService.BOARD_CARDS_NUMBER - table.getBoard().size()));

        table.getPlayerHands().forEach(p -> {
            if (p.getPlayerCards().size() < AllVariantsCalculationEquityService.PLAYER_CARDS_NUMBER)
                p.getPlayerCards().addAll(
                        cardDrawer.drawCards(AllVariantsCalculationEquityService.PLAYER_CARDS_NUMBER - p.getPlayerCards().size()));
        });
    }

}

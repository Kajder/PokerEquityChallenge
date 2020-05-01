package pokerequitychallenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pokerequitychallenge.card.Card;
import pokerequitychallenge.request.EquityRequest;
import pokerequitychallenge.player.PlayerHand;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CalculationDispatcherEquityService {

    private final AllVariantsCalculationEquityService allVariantsCalculationEquityService;
    private final ProbabilisticCalculationEquityService probabilisticCalculationEquityService;

    @Value("${calculation.probability.threshold: 2}")
    private int PROBABILITY_CALCULATION_THRESHOLD;

    public Map<Integer, Float> dispatchCalculation(EquityRequest equityRequest) {
        List<Card> requestCardsList = findAllCardsFromRequest(equityRequest);

        int undefinedCardsNumber = AllVariantsCalculationEquityService.BOARD_CARDS_NUMBER
                + (AllVariantsCalculationEquityService.PLAYER_CARDS_NUMBER * equityRequest.getPlayerHands().size()) - requestCardsList.size();

        return undefinedCardsNumber > PROBABILITY_CALCULATION_THRESHOLD
                ? probabilisticCalculationEquityService.runProbabilisticCalculation(equityRequest, requestCardsList)
                : allVariantsCalculationEquityService.runAllVariantsCalculation(equityRequest, requestCardsList, undefinedCardsNumber);
    }

    private List<Card> findAllCardsFromRequest(EquityRequest equityRequest) {
        return Stream.concat(
                equityRequest.getBoard().stream(),
                equityRequest.getPlayerHands().stream()
                        .map(PlayerHand::getPlayerCards)
                        .flatMap(Collection::stream))
                .collect(Collectors.toList());
    }

}

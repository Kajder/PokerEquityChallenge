package pokerequitychallenge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pokerequitychallenge.Table;
import pokerequitychallenge.card.Card;
import pokerequitychallenge.card.CardDeck;
import pokerequitychallenge.player.PlayerHand;
import pokerequitychallenge.request.EquityRequest;
import pokerequitychallenge.utils.EquityCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class AllVariantsCalculationEquityService {

    public static final int BOARD_CARDS_NUMBER = 5;
    public static final int PLAYER_CARDS_NUMBER = 2;

    public Map<Integer, Float> runAllVariantsCalculation(EquityRequest equityRequest,
                                                         List<Card> requestCardsList,
                                                         int undefinedCardsNumber) {

        List<Card> currentDeck = new ArrayList<>(CardDeck.deck);
        currentDeck.removeAll(requestCardsList);

        int permutationsNumber = 1;
        for (int i = 0; i < undefinedCardsNumber; i++) {
            permutationsNumber = permutationsNumber * (currentDeck.size() - i);
        }

        Map<Integer, Float> scores = equityRequest.getPlayerHands().stream().collect(
                Collectors.toMap(PlayerHand::getPlayerId, playerHand -> 0F));

        ArrayList<Integer> currentDeckCardsIndexes =
                (ArrayList<Integer>) IntStream.range(0, currentDeck.size()).boxed().collect(Collectors.toList());

        log.info(String.format("All variants calculation has been started\n" +
                        "Number of request's undefined cards: %d,\n" +
                        "Number of cards left in deck: %d,\n" +
                        "Number of all possible permutations of undefined cards number within current deck: %d",
                undefinedCardsNumber, currentDeck.size(), permutationsNumber));

        runPossibleGames(currentDeckCardsIndexes, currentDeck.size(), undefinedCardsNumber, equityRequest, scores, currentDeck);

        return EquityCalculator.convertScoresNumbersToPercentages(scores, permutationsNumber);
    }

    private static void runPossibleGames(ArrayList<Integer> currentDeckCardsIndexes,
                                         int n,
                                         int k,
                                         EquityRequest equityRequest,
                                         Map<Integer, Float> scores,
                                         List<Card> currentDeck) {
        if (k == 0) {
            ArrayList<Integer> singlePermutation = new ArrayList<>();
            for (int i = n; i < currentDeckCardsIndexes.size(); i++) {
                singlePermutation.add(currentDeckCardsIndexes.get(i));
            }
            Table table = new Table(equityRequest);
            drawAndDealMissingCards(table, currentDeck, singlePermutation);

            EquityCalculator.findWinnersAndWriteScores(table, scores);
            return;
        }

        for (int i = 0; i < n; i++) {
            swap(currentDeckCardsIndexes, i, n - 1);
            runPossibleGames(currentDeckCardsIndexes, n - 1, k - 1, equityRequest, scores, currentDeck);
            swap(currentDeckCardsIndexes, i, n - 1);
        }
    }

    private static void swap(ArrayList<Integer> a, int i, int j) {
        Integer temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    private static void drawAndDealMissingCards(Table table, List<Card> currentDeck, List<Integer> cardIndexesToDeal) {
        int cardToDealIndex = 0;

        while (table.getBoard().size() < BOARD_CARDS_NUMBER) {
            table.getBoard().add(currentDeck.get(cardIndexesToDeal.get(cardToDealIndex)));
            cardToDealIndex++;
            if (cardIndexesToDeal.size() <= cardToDealIndex) return;
        }

        while (currentDeck.size() > cardToDealIndex) {

            for (PlayerHand playerHand : table.getPlayerHands()) {

                while (playerHand.getPlayerCards().size() < PLAYER_CARDS_NUMBER) {
                    playerHand.getPlayerCards().add(currentDeck.get(cardIndexesToDeal.get(cardToDealIndex)));
                    cardToDealIndex++;
                    if (cardIndexesToDeal.size() <= cardToDealIndex) return;
                }
            }
        }
        throw new RuntimeException("Not all cards have been dealt");
    }

}

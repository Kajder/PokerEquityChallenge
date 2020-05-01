package pokerequitychallenge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pokerequitychallenge.*;
import pokerequitychallenge.card.Card;
import pokerequitychallenge.card.CardDeck;
import pokerequitychallenge.player.PlayerHand;
import pokerequitychallenge.request.EquityRequest;
import pokerequitychallenge.utils.EquityCalculator;
import pokerequitychallenge.utils.PermutationCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AllVariantsCalculationEquityService {

    public static final int BOARD_CARDS_NUMBER = 5;
    public static final int PLAYER_CARDS_NUMBER = 2;

    public Map<Integer, Float> runAllVariantsCalculation(EquityRequest equityRequest,
                                                         List<Card> requestCardsList,
                                                         int undefinedCardsNumber) {

        log.info("All variants calculation has been started");

        List<Card> currentDeck = new ArrayList<>(CardDeck.deck);
        currentDeck.removeAll(requestCardsList);

        ArrayList<ArrayList<Integer>> allPermutationsOfCardIndexes =
                PermutationCalculator.findKSizePermutationsOfNIndexes(undefinedCardsNumber, currentDeck.size());

        log.info(String.format("\nNumber of request's undefined cards: %d,\n" +
                        "Number of cards left in deck: %d,\n" +
                        "Number of all possible permutations of undefined cards number within current deck: %d",
                undefinedCardsNumber, currentDeck.size(), allPermutationsOfCardIndexes.size()));

        Map<Integer, Float> scores = equityRequest.getPlayerHands().stream().collect(
                Collectors.toMap(PlayerHand::getPlayerId, playerHand -> 0F));

        for (ArrayList<Integer> permutation : allPermutationsOfCardIndexes) {
            Table table = new Table(equityRequest);
            drawAndDealMissingCards(table, currentDeck, permutation);

            EquityCalculator.findWinnersAndWriteScores(table, scores);
        }

        return EquityCalculator.convertScoresNumbersToPercentages(scores, allPermutationsOfCardIndexes.size());
    }

    private void drawAndDealMissingCards(Table table, List<Card> currentDeck, List<Integer> cardIndexesToDeal) {
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

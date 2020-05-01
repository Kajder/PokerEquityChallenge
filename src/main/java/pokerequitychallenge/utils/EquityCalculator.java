package pokerequitychallenge.utils;

import lombok.extern.slf4j.Slf4j;
import pokerequitychallenge.hand.Hand;
import pokerequitychallenge.hand.HandsComparator;
import pokerequitychallenge.player.Player;
import pokerequitychallenge.Table;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class EquityCalculator {

    public static void findWinnersAndWriteScores(Table table, Map<Integer, Float> scores){

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

    public static Map<Integer, Float> convertScoresNumbersToPercentages(Map<Integer, Float> scores, int numberOfGames){
        scores.forEach((k, v) -> scores.put(k, (scores.get(k) / numberOfGames)));
        return scores;
    }

}

package pokerequitychallenge;

import org.junit.Test;
import pokerequitychallenge.card.Card;
import pokerequitychallenge.card.CardColor;
import pokerequitychallenge.card.CardType;
import pokerequitychallenge.hand.Hand;
import pokerequitychallenge.hand.HandType;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HandTypeCalculatorTest {

    @Test
    public void isRoyalFlush_Test() {
        //fine royal flush
        Hand royalFlushHand = new Hand(List.of(
                new Card(CardType.A, CardColor.DIAMOND),
                new Card(CardType.K, CardColor.DIAMOND),
                new Card(CardType.Q, CardColor.DIAMOND),
                new Card(CardType.J, CardColor.DIAMOND),
                new Card(CardType.TEN, CardColor.DIAMOND)));

        assertEquals(royalFlushHand.getHandType(), HandType.ROYAL_FLUSH);
    }

    @Test
    public void isRoyalFlush_WrongByCardType_Test() {
        //wrong royal flush - wrong card type
        Hand royalFlushWrongCardTypeHand = new Hand(List.of(
                new Card(CardType.A, CardColor.DIAMOND),
                new Card(CardType.K, CardColor.DIAMOND),
                new Card(CardType.Q, CardColor.DIAMOND),
                new Card(CardType.J, CardColor.DIAMOND),
                new Card(CardType.NINE, CardColor.DIAMOND)));

        assertNotEquals(royalFlushWrongCardTypeHand.getHandType(), HandType.ROYAL_FLUSH);
    }

    @Test
    public void isRoyalFlush_WrongByCardColor_Test() {
        //wrong royal flush - wrong card color
        Hand royalFlushWrongCardColorHand = new Hand(List.of(
                new Card(CardType.A, CardColor.DIAMOND),
                new Card(CardType.K, CardColor.DIAMOND),
                new Card(CardType.Q, CardColor.DIAMOND),
                new Card(CardType.J, CardColor.CLUB),
                new Card(CardType.TEN, CardColor.DIAMOND)));

        assertNotEquals(royalFlushWrongCardColorHand.getHandType(), HandType.ROYAL_FLUSH);
    }

    @Test
    public void isStraightFlush_Test() {
        //fine straight flush

        Hand straightFlushHand = new Hand(List.of(
                new Card(CardType.SIX, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FOUR, CardColor.CLUB),
                new Card(CardType.THREE, CardColor.CLUB),
                new Card(CardType.TWO, CardColor.CLUB)));

        assertEquals(straightFlushHand.getHandType(), HandType.STRAIGHT_FLUSH);
    }

    @Test
    public void isStraightFlushFromAce_Test() {
        //fine straight flush from Ace to Five
        Hand straightFlushCardsFromAceHand = new Hand(List.of(
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FOUR, CardColor.CLUB),
                new Card(CardType.THREE, CardColor.CLUB),
                new Card(CardType.TWO, CardColor.CLUB),
                new Card(CardType.A, CardColor.CLUB)));

        assertEquals(straightFlushCardsFromAceHand.getHandType(), HandType.STRAIGHT_FLUSH);
    }

    @Test
    public void isStraightFlush_WrongByCardColor_Test() {
        //wrong straight flush by card color
        Hand straightFlushWrongByCardColorHand = new Hand(List.of(
                new Card(CardType.SIX, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FOUR, CardColor.CLUB),
                new Card(CardType.THREE, CardColor.CLUB),
                new Card(CardType.TWO, CardColor.DIAMOND)));

        assertNotEquals(straightFlushWrongByCardColorHand.getHandType(), HandType.STRAIGHT_FLUSH);
    }

    @Test
    public void isStraightFlush_WrongByCardType_Test() {
        //wrong straight flush by card type
        Hand straightFlushWrongByCardTypeHand = new Hand(List.of(
                new Card(CardType.SIX, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FOUR, CardColor.CLUB),
                new Card(CardType.THREE, CardColor.CLUB),
                new Card(CardType.THREE, CardColor.CLUB)));

        assertNotEquals(straightFlushWrongByCardTypeHand.getHandType(), HandType.STRAIGHT_FLUSH);
    }

    @Test
    public void isFourOfKind_Test() {
        //fine four of a kind
        Hand fourOfKindHand = new Hand(List.of(
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.DIAMOND),
                new Card(CardType.FIVE, CardColor.HEART),
                new Card(CardType.FIVE, CardColor.SPADE),
                new Card(CardType.A, CardColor.CLUB)));

        assertEquals(fourOfKindHand.getHandType(), HandType.FOUR_OF_A_KIND);
    }

    @Test
    public void isFourOfKind_Wrong_Test() {
        //wrong four of a kind
        Hand fourOfKindWrongHand = new Hand(List.of(
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.DIAMOND),
                new Card(CardType.FIVE, CardColor.HEART),
                new Card(CardType.Q, CardColor.SPADE),
                new Card(CardType.A, CardColor.CLUB)));

        assertNotEquals(fourOfKindWrongHand.getHandType(), HandType.FOUR_OF_A_KIND);
    }

    @Test
    public void isFullHouse_Test() {
        //fine full house
        Hand fullHouseHand = new Hand(List.of(
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.DIAMOND),
                new Card(CardType.FIVE, CardColor.HEART),
                new Card(CardType.Q, CardColor.SPADE),
                new Card(CardType.Q, CardColor.CLUB)));

        assertEquals(fullHouseHand.getHandType(), HandType.FULL_HOUSE);
    }

    @Test
    public void isFullHouse_Wrong_Test() {
        //wrong full house
        Hand fullHouseWrongHand = new Hand(List.of(
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.DIAMOND),
                new Card(CardType.FIVE, CardColor.HEART),
                new Card(CardType.A, CardColor.SPADE),
                new Card(CardType.Q, CardColor.CLUB)));

        assertNotEquals(fullHouseWrongHand.getHandType(), HandType.FULL_HOUSE);
    }

    @Test
    public void isFlush_Test() {
        //fine flush
        Hand flushHand = new Hand(List.of(
                new Card(CardType.TWO, CardColor.CLUB),
                new Card(CardType.J, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.A, CardColor.CLUB),
                new Card(CardType.Q, CardColor.CLUB)));

        assertEquals(flushHand.getHandType(), HandType.FLUSH);
    }

    @Test
    public void isFlush_Wrong_Test() {
        //wrong flush
        Hand flushWrongHand = new Hand(List.of(
                new Card(CardType.TWO, CardColor.CLUB),
                new Card(CardType.J, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.A, CardColor.CLUB),
                new Card(CardType.Q, CardColor.DIAMOND)));

        assertNotEquals(flushWrongHand.getHandType(), HandType.FLUSH);
    }

    @Test
    public void isStraight_Test() {
        //fine straight
        Hand straightHand = new Hand(List.of(
                new Card(CardType.SIX, CardColor.CLUB),
                new Card(CardType.NINE, CardColor.DIAMOND),
                new Card(CardType.SEVEN, CardColor.CLUB),
                new Card(CardType.TEN, CardColor.SPADE),
                new Card(CardType.EIGHT, CardColor.HEART)));

        assertEquals(straightHand.getHandType(), HandType.STRAIGHT);
    }

    @Test
    public void isStraight_Wrong_Test() {
        //wrong straight
        Hand straightWrongHand = new Hand(List.of(
                new Card(CardType.SIX, CardColor.CLUB),
                new Card(CardType.NINE, CardColor.DIAMOND),
                new Card(CardType.A, CardColor.CLUB),
                new Card(CardType.TEN, CardColor.SPADE),
                new Card(CardType.EIGHT, CardColor.HEART)));

        assertNotEquals(straightWrongHand.getHandType(), HandType.STRAIGHT);
    }

    @Test
    public void isThreeOfKind_Test() {
        //fine three of a kind
        Hand threeOfKindHand = new Hand(List.of(
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.DIAMOND),
                new Card(CardType.FIVE, CardColor.HEART),
                new Card(CardType.TWO, CardColor.SPADE),
                new Card(CardType.A, CardColor.CLUB)));

        assertEquals(threeOfKindHand.getHandType(), HandType.THREE_OF_A_KIND);
    }

    @Test
    public void isThreeOfKind_Wrong_Test() {
        //wrong three of a kind
        Hand threeOfKindWrongHand = new Hand(List.of(
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.DIAMOND),
                new Card(CardType.FIVE, CardColor.HEART),
                new Card(CardType.FIVE, CardColor.SPADE),
                new Card(CardType.A, CardColor.CLUB)));

        assertNotEquals(threeOfKindWrongHand.getHandType(), HandType.THREE_OF_A_KIND);
    }

    @Test
    public void isTwoPair_Test() {
        //fine two pair
        Hand twoPairHand = new Hand(List.of(
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.DIAMOND),
                new Card(CardType.TWO, CardColor.HEART),
                new Card(CardType.TWO, CardColor.SPADE),
                new Card(CardType.A, CardColor.CLUB)));

        assertEquals(twoPairHand.getHandType(), HandType.TWO_PAIR);
    }

    @Test
    public void isTwoPair_Wrong_Test() {
        //wrong two pair
        Hand twoPairWrongHand = new Hand(List.of(
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.DIAMOND),
                new Card(CardType.TWO, CardColor.HEART),
                new Card(CardType.J, CardColor.SPADE),
                new Card(CardType.A, CardColor.CLUB)));

        assertNotEquals(twoPairWrongHand.getHandType(), HandType.TWO_PAIR);
    }

    @Test
    public void isOnePair_Test() {
        //fine one pair
        Hand onePairHand = new Hand(List.of(
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FIVE, CardColor.DIAMOND),
                new Card(CardType.J, CardColor.HEART),
                new Card(CardType.TEN, CardColor.SPADE),
                new Card(CardType.A, CardColor.CLUB)));

        assertEquals(onePairHand.getHandType(), HandType.ONE_PAIR);
    }

    @Test
    public void isOnePair_Wrong_Test() {
        //wrong one pair
        Hand onePairWrongHand = new Hand(List.of(
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FOUR, CardColor.DIAMOND),
                new Card(CardType.TEN, CardColor.HEART),
                new Card(CardType.J, CardColor.SPADE),
                new Card(CardType.A, CardColor.CLUB)));

        assertNotEquals(onePairWrongHand.getHandType(), HandType.ONE_PAIR);
    }

    @Test
    public void isHighCard_Test() {
        Hand highCardHand = new Hand(List.of(
                new Card(CardType.FIVE, CardColor.CLUB),
                new Card(CardType.FOUR, CardColor.DIAMOND),
                new Card(CardType.TEN, CardColor.HEART),
                new Card(CardType.J, CardColor.SPADE),
                new Card(CardType.A, CardColor.CLUB)));

        assertEquals(highCardHand.getHandType(), HandType.HIGH_CARD);
    }
}
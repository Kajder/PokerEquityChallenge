package pokerequitychallenge;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class HandTypeCalculatorTest {
    //HandTypeCalculator - HTC
    private HandTypeCalculator royalFlushHTC;
    private HandTypeCalculator royalFlushWrongCardTypeHTC;
    private HandTypeCalculator royalFlushWrongCardColorHTC;
    private HandTypeCalculator straightFlushHTC;
    private HandTypeCalculator straightFlushCardsFromAceHTC;
    private HandTypeCalculator straightFlushWrongByCardColorHTC;
    private HandTypeCalculator straightFlushWrongByCardTypeHTC;
    private HandTypeCalculator fourOfKindHTC;
    private HandTypeCalculator fourOfKindWrongHTC;
    private HandTypeCalculator fullHouseHTC;
    private HandTypeCalculator fullHouseWrongHTC;
    private HandTypeCalculator flushHTC;
    private HandTypeCalculator flushWrongHTC;
    private HandTypeCalculator straightHTC;
    private HandTypeCalculator straightWrongHTC;
    private HandTypeCalculator threeOfKindHTC;
    private HandTypeCalculator threeOfKindWrongHTC;
    private HandTypeCalculator twoPairHTC;
    private HandTypeCalculator twoPairWrongHTC;
    private HandTypeCalculator onePairHTC;
    private HandTypeCalculator onePairWrongHTC;
    private HandTypeCalculator highCardHTC;


    @Test
    public void isRoyalFlush_Test() {
        //fine royal flush
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.A, CardColor.DIAMOND));
        list.add(new Card(CardType.K, CardColor.DIAMOND));
        list.add(new Card(CardType.Q, CardColor.DIAMOND));
        list.add(new Card(CardType.J, CardColor.DIAMOND));
        list.add(new Card(CardType.TEN, CardColor.DIAMOND));
        royalFlushHTC = new HandTypeCalculator(list);
        assertEquals(royalFlushHTC.getHandType(), HandType.ROYAL_FLUSH);
        assertTrue(royalFlushHTC.getIsRoyalFlush());
    }

    @Test
    public void isRoyalFlush_WrongByCardType_Test() {
        //wrong royal flush - wrong card type
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.A, CardColor.DIAMOND));
        list.add(new Card(CardType.K, CardColor.DIAMOND));
        list.add(new Card(CardType.Q, CardColor.DIAMOND));
        list.add(new Card(CardType.J, CardColor.DIAMOND));
        list.add(new Card(CardType.NINE, CardColor.DIAMOND));
        royalFlushWrongCardTypeHTC = new HandTypeCalculator(list);
        assertNotEquals(royalFlushWrongCardTypeHTC.getHandType(), HandType.ROYAL_FLUSH);
        assertFalse(royalFlushWrongCardTypeHTC.getIsRoyalFlush());
    }

    @Test
    public void isRoyalFlush_WrongByCardColor_Test() {
        //wrong royal flush - wrong card color
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.A, CardColor.DIAMOND));
        list.add(new Card(CardType.K, CardColor.DIAMOND));
        list.add(new Card(CardType.Q, CardColor.DIAMOND));
        list.add(new Card(CardType.J, CardColor.CLUB));
        list.add(new Card(CardType.TEN, CardColor.DIAMOND));
        royalFlushWrongCardColorHTC = new HandTypeCalculator(list);
        assertNotEquals(royalFlushWrongCardColorHTC.getHandType(), HandType.ROYAL_FLUSH);
        assertFalse(royalFlushWrongCardColorHTC.getIsRoyalFlush());
    }

    @Test
    public void isStraightFlush_Test() {
        //fine straight flush
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.SIX, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FOUR, CardColor.CLUB));
        list.add(new Card(CardType.THREE, CardColor.CLUB));
        list.add(new Card(CardType.TWO, CardColor.CLUB));
        straightFlushHTC = new HandTypeCalculator(list);
        assertEquals(straightFlushHTC.getHandType(), HandType.STRAIGHT_FLUSH);
        assertTrue(straightFlushHTC.getIsStraight() && straightFlushHTC.getIsFlush());
    }

    @Test
    public void isStraightFlushFromAce_Test() {
        //fine straight flush from Ace to Five
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FOUR, CardColor.CLUB));
        list.add(new Card(CardType.THREE, CardColor.CLUB));
        list.add(new Card(CardType.TWO, CardColor.CLUB));
        list.add(new Card(CardType.A, CardColor.CLUB));
        straightFlushCardsFromAceHTC = new HandTypeCalculator(list);
        assertEquals(straightFlushCardsFromAceHTC.getHandType(), HandType.STRAIGHT_FLUSH);
        assertTrue(straightFlushCardsFromAceHTC.getIsStraight() && straightFlushCardsFromAceHTC.getIsFlush());
    }

    @Test
    public void isStraightFlush_WrongByCardColor_Test() {
        //wrong straight flush by card color
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.SIX, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FOUR, CardColor.CLUB));
        list.add(new Card(CardType.THREE, CardColor.CLUB));
        list.add(new Card(CardType.TWO, CardColor.DIAMOND));
        straightFlushWrongByCardColorHTC = new HandTypeCalculator(list);
        assertNotEquals(straightFlushWrongByCardColorHTC.getHandType(), HandType.STRAIGHT_FLUSH);
        assertFalse(straightFlushWrongByCardColorHTC.getIsStraight() && straightFlushWrongByCardColorHTC.getIsFlush());
    }

    @Test
    public void isStraightFlush_WrongByCardType_Test() {
        //wrong straight flush by card type
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.SIX, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FOUR, CardColor.CLUB));
        list.add(new Card(CardType.THREE, CardColor.CLUB));
        list.add(new Card(CardType.THREE, CardColor.CLUB));
        straightFlushWrongByCardTypeHTC = new HandTypeCalculator(list);
        assertNotEquals(straightFlushWrongByCardTypeHTC.getHandType(), HandType.STRAIGHT_FLUSH);
        assertFalse(straightFlushWrongByCardTypeHTC.getIsStraight() && straightFlushWrongByCardTypeHTC.getIsFlush());
    }

    @Test
    public void isFourOfKind_Test() {
        //fine four of a kind
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.DIAMOND));
        list.add(new Card(CardType.FIVE, CardColor.HEART));
        list.add(new Card(CardType.FIVE, CardColor.SPADE));
        list.add(new Card(CardType.A, CardColor.CLUB));
        fourOfKindHTC = new HandTypeCalculator(list);
        assertEquals(fourOfKindHTC.getHandType(), HandType.FOUR_OF_A_KIND);
        assertTrue(fourOfKindHTC.getIsFourOfKind());
    }

    @Test
    public void isFourOfKind_Wrong_Test() {
        //wrong four of a kind
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.DIAMOND));
        list.add(new Card(CardType.FIVE, CardColor.HEART));
        list.add(new Card(CardType.Q, CardColor.SPADE));
        list.add(new Card(CardType.A, CardColor.CLUB));
        fourOfKindWrongHTC = new HandTypeCalculator(list);
        assertNotEquals(fourOfKindWrongHTC.getHandType(), HandType.FOUR_OF_A_KIND);
        assertFalse(fourOfKindWrongHTC.getIsFourOfKind());
    }

    @Test
    public void isFullHouse_Test() {
        //fine full house
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.DIAMOND));
        list.add(new Card(CardType.FIVE, CardColor.HEART));
        list.add(new Card(CardType.Q, CardColor.SPADE));
        list.add(new Card(CardType.Q, CardColor.CLUB));
        fullHouseHTC = new HandTypeCalculator(list);
        assertEquals(fullHouseHTC.getHandType(), HandType.FULL_HOUSE);
        assertTrue(fullHouseHTC.getIsFullHouse());
    }

    @Test
    public void isFullHouse_Wrong_Test() {
        //wrong full house
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.DIAMOND));
        list.add(new Card(CardType.FIVE, CardColor.HEART));
        list.add(new Card(CardType.A, CardColor.SPADE));
        list.add(new Card(CardType.Q, CardColor.CLUB));
        fullHouseWrongHTC = new HandTypeCalculator(list);
        assertNotEquals(fullHouseWrongHTC.getHandType(), HandType.FULL_HOUSE);
        assertFalse(fullHouseWrongHTC.getIsFullHouse());
    }

    @Test
    public void isFlush_Test() {
        //fine flush
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.TWO, CardColor.CLUB));
        list.add(new Card(CardType.J, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.A, CardColor.CLUB));
        list.add(new Card(CardType.Q, CardColor.CLUB));
        flushHTC = new HandTypeCalculator(list);
        assertEquals(flushHTC.getHandType(), HandType.FLUSH);
        assertTrue(flushHTC.getIsFlush());
    }

    @Test
    public void isFlush_Wrong_Test() {
        //wrong flush
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.TWO, CardColor.CLUB));
        list.add(new Card(CardType.J, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.A, CardColor.CLUB));
        list.add(new Card(CardType.Q, CardColor.DIAMOND));
        flushWrongHTC = new HandTypeCalculator(list);
        assertNotEquals(flushWrongHTC.getHandType(), HandType.FLUSH);
        assertFalse(flushWrongHTC.getIsFlush());
    }

    @Test
    public void isStraight_Test() {
        //fine straight
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.SIX, CardColor.CLUB));
        list.add(new Card(CardType.NINE, CardColor.DIAMOND));
        list.add(new Card(CardType.SEVEN, CardColor.CLUB));
        list.add(new Card(CardType.TEN, CardColor.SPADE));
        list.add(new Card(CardType.EIGHT, CardColor.HEART));
        straightHTC = new HandTypeCalculator(list);
        assertEquals(straightHTC.getHandType(), HandType.STRAIGHT);
        assertTrue(straightHTC.getIsStraight());
    }

    @Test
    public void isStraight_Wrong_Test() {
        //wrong straight
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.SIX, CardColor.CLUB));
        list.add(new Card(CardType.NINE, CardColor.DIAMOND));
        list.add(new Card(CardType.A, CardColor.CLUB));
        list.add(new Card(CardType.TEN, CardColor.SPADE));
        list.add(new Card(CardType.EIGHT, CardColor.HEART));
        straightWrongHTC = new HandTypeCalculator(list);
        assertNotEquals(straightWrongHTC.getHandType(), HandType.STRAIGHT);
        assertFalse(straightWrongHTC.getIsFlush());
    }

    @Test
    public void isThreeOfKind_Test() {
        //fine three of a kind
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.DIAMOND));
        list.add(new Card(CardType.FIVE, CardColor.HEART));
        list.add(new Card(CardType.TWO, CardColor.SPADE));
        list.add(new Card(CardType.A, CardColor.CLUB));
        threeOfKindHTC = new HandTypeCalculator(list);
        assertEquals(threeOfKindHTC.getHandType(), HandType.THREE_OF_A_KIND);
        assertTrue(threeOfKindHTC.getIsThreeOfKind());
    }

    @Test
    public void isThreeOfKind_Wrong_Test() {
        //wrong three of a kind
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.DIAMOND));
        list.add(new Card(CardType.FIVE, CardColor.HEART));
        list.add(new Card(CardType.FIVE, CardColor.SPADE));
        list.add(new Card(CardType.A, CardColor.CLUB));
        threeOfKindWrongHTC = new HandTypeCalculator(list);
        assertNotEquals(threeOfKindWrongHTC.getHandType(), HandType.THREE_OF_A_KIND);
        assertFalse(threeOfKindWrongHTC.getIsThreeOfKind());
    }

    @Test
    public void isTwoPair_Test() {
        //fine two pair
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.DIAMOND));
        list.add(new Card(CardType.TWO, CardColor.HEART));
        list.add(new Card(CardType.TWO, CardColor.SPADE));
        list.add(new Card(CardType.A, CardColor.CLUB));
        twoPairHTC = new HandTypeCalculator(list);
        assertEquals(twoPairHTC.getHandType(), HandType.TWO_PAIR);
        assertTrue(twoPairHTC.getNumberOfPairs() == 2);
    }

    @Test
    public void isTwoPair_Wrong_Test() {
        //wrong two pair
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.DIAMOND));
        list.add(new Card(CardType.TWO, CardColor.HEART));
        list.add(new Card(CardType.J, CardColor.SPADE));
        list.add(new Card(CardType.A, CardColor.CLUB));
        twoPairWrongHTC = new HandTypeCalculator(list);
        assertNotEquals(twoPairWrongHTC.getHandType(), HandType.TWO_PAIR);
        assertFalse(twoPairWrongHTC.getNumberOfPairs() == 2);
    }

    @Test
    public void isOnePair_Test() {
        //fine one pair
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FIVE, CardColor.DIAMOND));
        list.add(new Card(CardType.J, CardColor.HEART));
        list.add(new Card(CardType.TEN, CardColor.SPADE));
        list.add(new Card(CardType.A, CardColor.CLUB));
        onePairHTC = new HandTypeCalculator(list);
        assertEquals(onePairHTC.getHandType(), HandType.ONE_PAIR);
        assertTrue(onePairHTC.getNumberOfPairs() == 1);
    }

    @Test
    public void isOnePair_Wrong_Test() {
        //wrong one pair
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FOUR, CardColor.DIAMOND));
        list.add(new Card(CardType.TEN, CardColor.HEART));
        list.add(new Card(CardType.J, CardColor.SPADE));
        list.add(new Card(CardType.A, CardColor.CLUB));
        onePairWrongHTC = new HandTypeCalculator(list);
        assertNotEquals(onePairWrongHTC.getHandType(), HandType.ONE_PAIR);
        assertFalse(onePairWrongHTC.getNumberOfPairs() == 1);
    }

    @Test
    public void isHighCard_Test() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(CardType.FIVE, CardColor.CLUB));
        list.add(new Card(CardType.FOUR, CardColor.DIAMOND));
        list.add(new Card(CardType.TEN, CardColor.HEART));
        list.add(new Card(CardType.J, CardColor.SPADE));
        list.add(new Card(CardType.A, CardColor.CLUB));
        highCardHTC = new HandTypeCalculator(list);
        assertEquals(highCardHTC.getHandType(), HandType.HIGH_CARD);
    }

}
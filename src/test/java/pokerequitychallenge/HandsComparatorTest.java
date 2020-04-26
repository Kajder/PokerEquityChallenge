package pokerequitychallenge;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class HandsComparatorTest {
    private static final CardType A = CardType.A;
    private static final CardType TWO = CardType.TWO;
    private static final CardType THREE = CardType.THREE;
    private static final CardType FOUR = CardType.FOUR;
    private static final CardType FIVE = CardType.FIVE;
    private static final CardType SIX = CardType.SIX;
    private static final CardType SEVEN = CardType.SEVEN;
    private static final CardType EIGHT = CardType.EIGHT;
    private static final CardType NINE = CardType.NINE;
    private static final CardType TEN = CardType.TEN;
    private static final CardType J = CardType.J;
    private static final CardType Q = CardType.Q;
    private static final CardType K = CardType.K;
    private static final CardColor DIAMOND = CardColor.DIAMOND;
    private static final CardColor CLUB = CardColor.CLUB;
    private static final CardColor HEART = CardColor.HEART;
    private static final CardColor SPADE = CardColor.SPADE;

    private static final List<Hand> handsList = new ArrayList<>();
    private static final List<Hand> outcomeHandsList = new ArrayList<>();

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////////PREPARING HANDS///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    private static final Hand royalFlushHand_1 = new Hand(A, DIAMOND, K, DIAMOND, Q, DIAMOND, J, DIAMOND, TEN, DIAMOND);
    private static final Hand royalFlushHand_2 = new Hand(A, CLUB, K, CLUB, Q, CLUB, J, CLUB, TEN, CLUB);
    private static final Hand straightFlushHand_1 = new Hand(SIX, CLUB, FIVE, CLUB, FOUR, CLUB, THREE, CLUB, TWO, CLUB);
    private static final Hand straightFlushHand_2 = new Hand(K, CLUB, Q, CLUB, J, CLUB, TEN, CLUB, NINE, CLUB);
    private static final Hand straightFlushHand_3 = new Hand(K, CLUB, Q, CLUB, J, CLUB, TEN, CLUB, NINE, CLUB);
    private static final Hand straightFlushHand_4 = new Hand(SEVEN, CLUB, SIX, CLUB, FIVE, CLUB, FOUR, CLUB, THREE, CLUB);
    private static final Hand straightFlushHand_5 = new Hand(A, CLUB, TWO, CLUB, FIVE, CLUB, FOUR, CLUB, THREE, CLUB);
    private static final Hand fourOfKindHand_1 = new Hand(FIVE, CLUB, FIVE, DIAMOND, FIVE, HEART, FIVE, SPADE, A, CLUB);
    private static final Hand fourOfKindHand_2 = new Hand(A, CLUB, A, DIAMOND, A, HEART, A, SPADE, J, CLUB);
    private static final Hand fourOfKindHand_3 = new Hand(A, CLUB, A, DIAMOND, A, HEART, A, SPADE, NINE, CLUB);
    private static final Hand fourOfKindHand_4 = new Hand(A, CLUB, A, DIAMOND, A, HEART, A, SPADE, TWO, CLUB);
    private static final Hand fullHouseHand_1 = new Hand(FIVE, CLUB, FIVE, DIAMOND, FIVE, HEART, Q, SPADE, Q, CLUB);
    private static final Hand fullHouseHand_2 = new Hand(A, CLUB, A, DIAMOND, A, HEART, Q, SPADE, Q, CLUB);
    private static final Hand fullHouseHand_3 = new Hand(A, CLUB, A, DIAMOND, A, HEART, K, SPADE, K, CLUB);
    private static final Hand fullHouseHand_4 = new Hand(A, CLUB, A, DIAMOND, TWO, HEART, TWO, SPADE, TWO, CLUB);
    private static final Hand flushHand_1 = new Hand(TWO, CLUB, J, CLUB, FIVE, CLUB, A, CLUB, Q, CLUB);
    private static final Hand flushHand_2 = new Hand(TWO, DIAMOND, J, DIAMOND, FIVE, DIAMOND, A, DIAMOND, Q, DIAMOND);
    private static final Hand flushHand_3 = new Hand(TWO, DIAMOND, J, DIAMOND, FIVE, DIAMOND, A, DIAMOND, Q, DIAMOND);
    private static final Hand flushHand_4 = new Hand(TWO, DIAMOND, J, DIAMOND, FIVE, DIAMOND, K, DIAMOND, Q, DIAMOND);
    private static final Hand flushHand_5 = new Hand(TWO, DIAMOND, J, DIAMOND, FIVE, DIAMOND, K, DIAMOND, TEN, DIAMOND);
    private static final Hand flushHand_6 = new Hand(TWO, DIAMOND, J, DIAMOND, FIVE, DIAMOND, K, DIAMOND, NINE, DIAMOND);
    private static final Hand flushHand_7 = new Hand(TWO, DIAMOND, J, DIAMOND, FOUR, DIAMOND, K, DIAMOND, NINE, DIAMOND);
    private static final Hand straightHand_1 = new Hand(SIX, CLUB, NINE, DIAMOND, SEVEN, CLUB, TEN, SPADE, EIGHT, HEART);
    private static final Hand straightHand_2 = new Hand(J, CLUB, NINE, DIAMOND, SEVEN, CLUB, TEN, SPADE, EIGHT, HEART);
    private static final Hand straightHand_3 = new Hand(A, DIAMOND, TWO, CLUB, FIVE, SPADE, FOUR, CLUB, THREE, HEART);
    private static final Hand straightHand_4 = new Hand(A, DIAMOND, TWO, CLUB, FIVE, SPADE, FOUR, CLUB, THREE, HEART);
    private static final Hand straightHand_5 = new Hand(A, HEART, K, CLUB, Q, CLUB, J, CLUB, TEN, CLUB);
    private static final Hand threeOfKindHand_1 = new Hand(FIVE, CLUB, FIVE, DIAMOND, FIVE, HEART, TWO, SPADE, A, CLUB);
    private static final Hand threeOfKindHand_2 = new Hand(FIVE, CLUB, TWO, DIAMOND, TWO, HEART, TWO, SPADE, A, CLUB);
    private static final Hand threeOfKindHand_3 = new Hand(NINE, CLUB, NINE, DIAMOND, NINE, HEART, TWO, SPADE, A, CLUB);
    private static final Hand threeOfKindHand_4 = new Hand(NINE, CLUB, NINE, DIAMOND, NINE, HEART, THREE, SPADE, A, CLUB);
    private static final Hand threeOfKindHand_5 = new Hand(FIVE, CLUB, TWO, DIAMOND, TWO, HEART, TWO, SPADE, K, CLUB);
    private static final Hand twoPairHand_1 = new Hand(A, CLUB, A, DIAMOND, SIX, HEART, SIX, SPADE, TWO, CLUB);
    private static final Hand twoPairHand_2 = new Hand(K, CLUB, K, DIAMOND, SIX, HEART, SIX, SPADE, A, CLUB);
    private static final Hand twoPairHand_3 = new Hand(SEVEN, CLUB, SEVEN, DIAMOND, FIVE, HEART, FIVE, SPADE, A, CLUB);
    private static final Hand twoPairHand_4 = new Hand(SEVEN, CLUB, SEVEN, DIAMOND, FIVE, HEART, FIVE, SPADE, SIX, CLUB);
    private static final Hand twoPairHand_5 = new Hand(SEVEN, CLUB, SEVEN, DIAMOND, FIVE, HEART, FIVE, SPADE, FOUR, CLUB);
    private static final Hand twoPairHand_6 = new Hand(SEVEN, CLUB, SEVEN, DIAMOND, FIVE, HEART, FIVE, SPADE, TWO, CLUB);
    private static final Hand onePairHand_1 = new Hand(A, CLUB, A, DIAMOND, Q, HEART, TEN, SPADE, NINE, CLUB);
    private static final Hand onePairHand_2 = new Hand(K, CLUB, K, DIAMOND, Q, HEART, TEN, SPADE, NINE, CLUB);
    private static final Hand onePairHand_3 = new Hand(K, CLUB, K, DIAMOND, J, HEART, TEN, SPADE, NINE, CLUB);
    private static final Hand onePairHand_4 = new Hand(K, CLUB, K, DIAMOND, J, HEART, NINE, SPADE, EIGHT, CLUB);
    private static final Hand onePairHand_5 = new Hand(K, CLUB, K, DIAMOND, J, HEART, NINE, SPADE, SEVEN, CLUB);
    private static final Hand highCardHand_1 = new Hand(A, CLUB, Q, DIAMOND, TEN, HEART, EIGHT, SPADE, SIX, CLUB);
    private static final Hand highCardHand_2 = new Hand(K, CLUB, J, DIAMOND, TEN, HEART, EIGHT, SPADE, SIX, CLUB);
    private static final Hand highCardHand_3 = new Hand(K, CLUB, NINE, DIAMOND, SEVEN, HEART, FIVE, SPADE, THREE, CLUB);
    private static final Hand highCardHand_4 = new Hand(K, CLUB, NINE, DIAMOND, SIX, HEART, FIVE, SPADE, THREE, CLUB);
    private static final Hand highCardHand_5 = new Hand(K, CLUB, NINE, DIAMOND, SIX, HEART, FOUR, SPADE, THREE, CLUB);
    private static final Hand highCardHand_6 = new Hand(K, CLUB, NINE, DIAMOND, SIX, HEART, FOUR, SPADE, TWO, CLUB);

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////////FINDING WINNERS///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    //region Any Hand Type

    private static final Hand hand1 = new Hand(TEN, CLUB, J, CLUB, K, CLUB, Q, HEART, SIX, CLUB);
    private static final Hand hand2 = new Hand(TEN, CLUB, J, CLUB, K, CLUB, SEVEN, SPADE, TEN, DIAMOND);

    @Test
    public void findWinnersFrom_AnyHands_Test() {
        handsList.clear();
        handsList.add(hand1);
        handsList.add(hand2);

        outcomeHandsList.clear();
        outcomeHandsList.add(hand2);

        assertEquals(HandsComparator.getWinningHands(handsList), outcomeHandsList);
    }
    //endregion

    //region Straight Flush
    @Test
    public void findWinnersFrom_StraightFlushes_Test() {
        handsList.clear();
        handsList.add(straightFlushHand_1);
        handsList.add(straightFlushHand_2);
        handsList.add(straightFlushHand_3);
        handsList.add(straightFlushHand_4);

        outcomeHandsList.clear();
        outcomeHandsList.add(straightFlushHand_2);
        outcomeHandsList.add(straightFlushHand_3);

        assertEquals(HandsComparator.findWinnersFrom_StraightFlushes(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_StraightFlushes_LowestVsHighest_Test() {
        handsList.clear();
        handsList.add(royalFlushHand_1);
        handsList.add(straightFlushHand_1);
        handsList.add(straightFlushHand_2);
        handsList.add(straightFlushHand_3);
        handsList.add(straightFlushHand_4);
        handsList.add(straightFlushHand_5);

        outcomeHandsList.clear();
        outcomeHandsList.add(royalFlushHand_1);

        assertEquals(HandsComparator.findWinnersFrom_StraightFlushes(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_StraightFlushes_WrongByDoubleHand_Test() {
        handsList.clear();
        handsList.add(straightFlushHand_1);
        handsList.add(straightFlushHand_2);
        handsList.add(straightFlushHand_3);
        handsList.add(straightFlushHand_4);

        outcomeHandsList.clear();
        outcomeHandsList.add(straightFlushHand_2);
        outcomeHandsList.add(straightFlushHand_2);

        assertNotEquals(HandsComparator.findWinnersFrom_StraightFlushes(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_StraightFlushes_WrongByAdditionalHand_Test() {
        handsList.clear();
        handsList.add(straightFlushHand_1);
        handsList.add(straightFlushHand_2);
        handsList.add(straightFlushHand_3);
        handsList.add(straightFlushHand_4);

        outcomeHandsList.clear();
        outcomeHandsList.add(straightFlushHand_2);
        outcomeHandsList.add(straightFlushHand_3);
        outcomeHandsList.add(straightFlushHand_4);

        assertNotEquals(HandsComparator.findWinnersFrom_StraightFlushes(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_StraightFlushes_WrongByNotEnoughHands_Test() {
        handsList.clear();
        handsList.add(straightFlushHand_1);
        handsList.add(straightFlushHand_2);
        handsList.add(straightFlushHand_3);
        handsList.add(straightFlushHand_4);

        outcomeHandsList.clear();
        outcomeHandsList.add(straightFlushHand_3);

        assertNotEquals(HandsComparator.findWinnersFrom_StraightFlushes(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_StraightFlushes_LowestStraight_VS_NotHighestStraight_Test() {
        handsList.clear();
        handsList.add(straightFlushHand_4);
        handsList.add(straightFlushHand_4);
        handsList.add(straightFlushHand_5);
        handsList.add(straightFlushHand_5);

        outcomeHandsList.clear();
        outcomeHandsList.add(straightFlushHand_4);
        outcomeHandsList.add(straightFlushHand_4);

        assertEquals(HandsComparator.findWinnersFrom_StraightFlushes(handsList), outcomeHandsList);
    }
    //endregion

    //region Four Of A Kind
    @Test
    public void findWinnersFrom_FourOfKinds_Test() {
        handsList.clear();
        handsList.add(fourOfKindHand_1);
        handsList.add(fourOfKindHand_2);
        handsList.add(fourOfKindHand_3);
        handsList.add(fourOfKindHand_4);

        outcomeHandsList.clear();
        outcomeHandsList.add(fourOfKindHand_2);

        assertEquals(HandsComparator.findWinnersFrom_FourOfKinds(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_FourOfKinds_DoubleHand_Test() {
        handsList.clear();
        handsList.add(fourOfKindHand_1);
        handsList.add(fourOfKindHand_2);
        handsList.add(fourOfKindHand_3);
        handsList.add(fourOfKindHand_4);
        handsList.add(fourOfKindHand_2);

        outcomeHandsList.clear();
        outcomeHandsList.add(fourOfKindHand_2);
        outcomeHandsList.add(fourOfKindHand_2);

        assertEquals(HandsComparator.findWinnersFrom_FourOfKinds(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_FourOfKinds_Wrong_Test() {
        handsList.clear();
        handsList.add(fourOfKindHand_1);
        handsList.add(fourOfKindHand_2);
        handsList.add(fourOfKindHand_3);
        handsList.add(fourOfKindHand_4);

        outcomeHandsList.clear();
        outcomeHandsList.add(fourOfKindHand_3);

        assertNotEquals(HandsComparator.findWinnersFrom_FourOfKinds(handsList), outcomeHandsList);
    }
    //endregion

    //region Full House
    @Test
    public void findWinnersFrom_FullHouses_Test() {
        handsList.clear();
        handsList.add(fullHouseHand_1);
        handsList.add(fullHouseHand_2);
        handsList.add(fullHouseHand_3);
        handsList.add(fullHouseHand_4);

        outcomeHandsList.clear();
        outcomeHandsList.add(fullHouseHand_3);

        assertEquals(HandsComparator.findWinnersFrom_FullHouses(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_FullHouses_DoubleHand_Test() {
        handsList.clear();
        handsList.add(fullHouseHand_1);
        handsList.add(fullHouseHand_2);
        handsList.add(fullHouseHand_2);
        handsList.add(fullHouseHand_3);
        handsList.add(fullHouseHand_4);
        handsList.add(fullHouseHand_3);
        handsList.add(fullHouseHand_4);

        outcomeHandsList.clear();
        outcomeHandsList.add(fullHouseHand_3);
        outcomeHandsList.add(fullHouseHand_3);

        assertEquals(HandsComparator.findWinnersFrom_FullHouses(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_FullHouses_Wrong_Test() {
        handsList.clear();
        handsList.add(fullHouseHand_1);
        handsList.add(fullHouseHand_2);
        handsList.add(fullHouseHand_3);
        handsList.add(fullHouseHand_4);

        outcomeHandsList.clear();
        outcomeHandsList.add(fullHouseHand_4);

        assertNotEquals(HandsComparator.findWinnersFrom_FullHouses(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_FullHouses_DoubleHand_Wrong_Test() {
        handsList.clear();
        handsList.add(fullHouseHand_1);
        handsList.add(fullHouseHand_2);
        handsList.add(fullHouseHand_2);
        handsList.add(fullHouseHand_3);
        handsList.add(fullHouseHand_4);
        handsList.add(fullHouseHand_3);
        handsList.add(fullHouseHand_4);

        outcomeHandsList.clear();
        outcomeHandsList.add(fullHouseHand_3);

        assertNotEquals(HandsComparator.findWinnersFrom_FullHouses(handsList), outcomeHandsList);
    }
    //endregion

    //region Flush
    @Test
    public void findWinnersFrom_Flushes_HighestHand() {
        handsList.clear();
        handsList.add(flushHand_3);
        handsList.add(flushHand_4);
        handsList.add(flushHand_5);
        handsList.add(flushHand_6);
        handsList.add(flushHand_7);

        outcomeHandsList.clear();
        outcomeHandsList.add(flushHand_3);

        assertEquals(HandsComparator.findWinnersFrom_Flushes(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_Flushes_TwoHighestHands() {
        handsList.clear();
        handsList.add(flushHand_2);
        handsList.add(flushHand_3);
        handsList.add(flushHand_4);
        handsList.add(flushHand_5);
        handsList.add(flushHand_6);
        handsList.add(flushHand_7);

        outcomeHandsList.clear();
        outcomeHandsList.add(flushHand_2);
        outcomeHandsList.add(flushHand_3);

        assertEquals(HandsComparator.findWinnersFrom_Flushes(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_Flushes_BySecondHighestHand() {
        handsList.clear();
        handsList.add(flushHand_4);
        handsList.add(flushHand_5);
        handsList.add(flushHand_6);
        handsList.add(flushHand_7);

        outcomeHandsList.clear();
        outcomeHandsList.add(flushHand_4);

        assertEquals(HandsComparator.findWinnersFrom_Flushes(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_Flushes_ByThirdHighestHand() {
        handsList.clear();
        handsList.add(flushHand_5);
        handsList.add(flushHand_6);
        handsList.add(flushHand_7);

        outcomeHandsList.clear();
        outcomeHandsList.add(flushHand_5);

        assertEquals(HandsComparator.findWinnersFrom_Flushes(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_Flushes_ByFourthHighestHand() {
        handsList.clear();
        handsList.add(flushHand_6);
        handsList.add(flushHand_7);

        outcomeHandsList.clear();
        outcomeHandsList.add(flushHand_6);

        assertEquals(HandsComparator.findWinnersFrom_Flushes(handsList), outcomeHandsList);
    }
    //endregion

    //region Straight
    @Test
    public void findWinnersFrom_Straights_Test() {
        handsList.clear();
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(straightHand_3);
        handsList.add(straightHand_4);
        handsList.add(straightHand_5);

        outcomeHandsList.clear();
        outcomeHandsList.add(straightHand_5);

        assertEquals(HandsComparator.findWinnersFrom_Straights(handsList), outcomeHandsList);
    }
    //endregion

    //region Three Of A Kind
    @Test
    public void findWinnersFrom_ThreeOfKinds_DifferentSecondCard_Test() {
        handsList.clear();
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(threeOfKindHand_3);
        handsList.add(threeOfKindHand_4);

        outcomeHandsList.clear();
        outcomeHandsList.add(threeOfKindHand_4);

        assertEquals(HandsComparator.findWinnersFrom_ThreeOfKinds(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_ThreeOfKinds_DifferentSecondCard_TwoHands_Test() {
        handsList.clear();
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(threeOfKindHand_3);
        handsList.add(threeOfKindHand_4);
        handsList.add(threeOfKindHand_4);

        outcomeHandsList.clear();
        outcomeHandsList.add(threeOfKindHand_4);
        outcomeHandsList.add(threeOfKindHand_4);

        assertEquals(HandsComparator.findWinnersFrom_ThreeOfKinds(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_ThreeOfKinds_DifferentFirstCard_Test() {
        handsList.clear();
        handsList.add(threeOfKindHand_2);
        handsList.add(threeOfKindHand_5);

        outcomeHandsList.clear();
        outcomeHandsList.add(threeOfKindHand_2);

        assertEquals(HandsComparator.findWinnersFrom_ThreeOfKinds(handsList), outcomeHandsList);
    }
    //endregion

    //region Two Pair
    @Test
    public void findWinnersFrom_TwoPairs_FirstPairDoubleHand_Test() {
        handsList.clear();
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(twoPairHand_3);
        handsList.add(twoPairHand_4);
        handsList.add(twoPairHand_5);
        handsList.add(twoPairHand_6);

        outcomeHandsList.clear();
        outcomeHandsList.add(twoPairHand_1);
        outcomeHandsList.add(twoPairHand_1);

        assertEquals(HandsComparator.findWinnersFrom_TwoPairs(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_TwoPairs_FirstPairUnique_Test() {
        handsList.clear();
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(twoPairHand_3);
        handsList.add(twoPairHand_4);
        handsList.add(twoPairHand_5);
        handsList.add(twoPairHand_6);

        outcomeHandsList.clear();
        outcomeHandsList.add(twoPairHand_1);

        assertEquals(HandsComparator.findWinnersFrom_TwoPairs(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_TwoPairs_SecondPairUnique_Test() {
        handsList.clear();
        handsList.add(twoPairHand_2);
        handsList.add(twoPairHand_3);
        handsList.add(twoPairHand_4);
        handsList.add(twoPairHand_5);
        handsList.add(twoPairHand_6);

        outcomeHandsList.clear();
        outcomeHandsList.add(twoPairHand_2);

        assertEquals(HandsComparator.findWinnersFrom_TwoPairs(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_TwoPairs_SingleCardUniqueAndHighest_Test() {
        handsList.clear();
        handsList.add(twoPairHand_3);
        handsList.add(twoPairHand_4);
        handsList.add(twoPairHand_5);
        handsList.add(twoPairHand_6);

        outcomeHandsList.clear();
        outcomeHandsList.add(twoPairHand_3);

        assertEquals(HandsComparator.findWinnersFrom_TwoPairs(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_TwoPairs_SingleCardUniqueAndMiddle_Test() {
        handsList.clear();
        handsList.add(twoPairHand_4);
        handsList.add(twoPairHand_5);
        handsList.add(twoPairHand_6);

        outcomeHandsList.clear();
        outcomeHandsList.add(twoPairHand_4);

        assertEquals(HandsComparator.findWinnersFrom_TwoPairs(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_TwoPairs_SingleCardUniqueAndLowest_Test() {
        handsList.clear();
        handsList.add(twoPairHand_5);
        handsList.add(twoPairHand_6);

        outcomeHandsList.clear();
        outcomeHandsList.add(twoPairHand_5);

        assertEquals(HandsComparator.findWinnersFrom_TwoPairs(handsList), outcomeHandsList);
    }
    //endregion

    //region One Pair
    @Test
    public void findWinnersFrom_OnePair_HighestPair_Test() {
        handsList.clear();
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(onePairHand_3);
        handsList.add(onePairHand_4);
        handsList.add(onePairHand_5);

        outcomeHandsList.clear();
        outcomeHandsList.add(onePairHand_1);

        assertEquals(HandsComparator.findWinnersFrom_OnePairs(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_OnePair_DoubleHighestPair_Test() {
        handsList.clear();
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(onePairHand_3);
        handsList.add(onePairHand_4);
        handsList.add(onePairHand_5);

        outcomeHandsList.clear();
        outcomeHandsList.add(onePairHand_1);
        outcomeHandsList.add(onePairHand_1);

        assertEquals(HandsComparator.findWinnersFrom_OnePairs(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_OnePair_HighestSingle_Test() {
        handsList.clear();
        handsList.add(onePairHand_2);
        handsList.add(onePairHand_3);
        handsList.add(onePairHand_4);
        handsList.add(onePairHand_5);

        outcomeHandsList.clear();
        outcomeHandsList.add(onePairHand_2);

        assertEquals(HandsComparator.findWinnersFrom_OnePairs(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_OnePair_SecondHighestSingle_Test() {
        handsList.clear();
        handsList.add(onePairHand_3);
        handsList.add(onePairHand_4);
        handsList.add(onePairHand_5);

        outcomeHandsList.clear();
        outcomeHandsList.add(onePairHand_3);

        assertEquals(HandsComparator.findWinnersFrom_OnePairs(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_OnePair_ThirdHighestSingle_Test() {
        handsList.clear();
        handsList.add(onePairHand_4);
        handsList.add(onePairHand_5);

        outcomeHandsList.clear();
        outcomeHandsList.add(onePairHand_4);

        assertEquals(HandsComparator.findWinnersFrom_OnePairs(handsList), outcomeHandsList);
    }
    //endregion

    //region High Card
    @Test
    public void findWinnersFrom_HighCard_HighestFirst_Test() {
        handsList.clear();
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);
        handsList.add(highCardHand_3);
        handsList.add(highCardHand_4);
        handsList.add(highCardHand_5);
        handsList.add(highCardHand_6);

        outcomeHandsList.clear();
        outcomeHandsList.add(highCardHand_1);

        assertEquals(HandsComparator.findWinnersFrom_HighCards(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_HighCard_HighestSecond_Test() {
        handsList.clear();
        handsList.add(highCardHand_2);
        handsList.add(highCardHand_3);
        handsList.add(highCardHand_4);
        handsList.add(highCardHand_5);
        handsList.add(highCardHand_6);

        outcomeHandsList.clear();
        outcomeHandsList.add(highCardHand_2);

        assertEquals(HandsComparator.findWinnersFrom_HighCards(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_HighCard_HighestThird_Test() {
        handsList.clear();
        handsList.add(highCardHand_3);
        handsList.add(highCardHand_4);
        handsList.add(highCardHand_5);
        handsList.add(highCardHand_6);

        outcomeHandsList.clear();
        outcomeHandsList.add(highCardHand_3);

        assertEquals(HandsComparator.findWinnersFrom_HighCards(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_HighCard_HighestFourth_Test() {
        handsList.clear();
        handsList.add(highCardHand_4);
        handsList.add(highCardHand_5);
        handsList.add(highCardHand_6);

        outcomeHandsList.clear();
        outcomeHandsList.add(highCardHand_4);

        assertEquals(HandsComparator.findWinnersFrom_HighCards(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_HighCard_HighestFifth_Test() {
        handsList.clear();
        handsList.add(highCardHand_5);
        handsList.add(highCardHand_6);

        outcomeHandsList.clear();
        outcomeHandsList.add(highCardHand_5);

        assertEquals(HandsComparator.findWinnersFrom_HighCards(handsList), outcomeHandsList);
    }

    @Test
    public void findWinnersFrom_HighCard_DoubleHighestFifth_Test() {
        handsList.clear();
        handsList.add(highCardHand_5);
        handsList.add(highCardHand_5);
        handsList.add(highCardHand_6);

        outcomeHandsList.clear();
        outcomeHandsList.add(highCardHand_5);
        outcomeHandsList.add(highCardHand_5);

        assertEquals(HandsComparator.findWinnersFrom_HighCards(handsList), outcomeHandsList);
    }
    //endregion

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////GETTING HIGHEST HAND TYPES/////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    //region Royal Flush
    @Test
    public void getHighestHandType_RoyalFlush_Test() {
        handsList.clear();
        handsList.add(royalFlushHand_1);
        handsList.add(royalFlushHand_2);
        handsList.add(straightFlushHand_1);
        handsList.add(straightFlushHand_2);
        handsList.add(fourOfKindHand_1);
        handsList.add(fourOfKindHand_2);
        handsList.add(fullHouseHand_1);
        handsList.add(fullHouseHand_2);
        handsList.add(flushHand_1);
        handsList.add(flushHand_2);
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        assertEquals(HandsComparator.getHighestHandType(handsList), HandType.ROYAL_FLUSH);
    }

    @Test
    public void getHandsWithHighestHandTypeList_RoyalFlush_Test() {
        handsList.clear();
        handsList.add(royalFlushHand_1);
        handsList.add(royalFlushHand_2);
        handsList.add(straightFlushHand_1);
        handsList.add(straightFlushHand_2);
        handsList.add(fourOfKindHand_1);
        handsList.add(fourOfKindHand_2);
        handsList.add(fullHouseHand_1);
        handsList.add(fullHouseHand_2);
        handsList.add(flushHand_1);
        handsList.add(flushHand_2);
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        outcomeHandsList.clear();
        outcomeHandsList.add(royalFlushHand_1);
        outcomeHandsList.add(royalFlushHand_2);
        assertEquals(HandsComparator.getHandsWithHighestHandTypeList(handsList), outcomeHandsList);
    }
    //endregion

    //region Straight Flush
    @Test
    public void getHighestHandType_StraightFlush_Test() {
        handsList.clear();
        handsList.add(straightFlushHand_1);
        handsList.add(straightFlushHand_2);
        handsList.add(fourOfKindHand_1);
        handsList.add(fourOfKindHand_2);
        handsList.add(fullHouseHand_1);
        handsList.add(fullHouseHand_2);
        handsList.add(flushHand_1);
        handsList.add(flushHand_2);
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        assertEquals(HandsComparator.getHighestHandType(handsList), HandType.STRAIGHT_FLUSH);
    }

    @Test
    public void getHandsWithHighestHandTypeList_StraightFlush_Test() {
        handsList.clear();
        handsList.add(straightFlushHand_1);
        handsList.add(straightFlushHand_2);
        handsList.add(fourOfKindHand_1);
        handsList.add(fourOfKindHand_2);
        handsList.add(fullHouseHand_1);
        handsList.add(fullHouseHand_2);
        handsList.add(flushHand_1);
        handsList.add(flushHand_2);
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        outcomeHandsList.clear();
        outcomeHandsList.add(straightFlushHand_1);
        outcomeHandsList.add(straightFlushHand_2);
        assertEquals(HandsComparator.getHandsWithHighestHandTypeList(handsList), outcomeHandsList);
    }
    //endregion

    //region Four Of A Kind
    @Test
    public void getHighestHandType_FourOfKind_Test() {
        handsList.clear();
        handsList.add(fourOfKindHand_1);
        handsList.add(fourOfKindHand_2);
        handsList.add(fullHouseHand_1);
        handsList.add(fullHouseHand_2);
        handsList.add(flushHand_1);
        handsList.add(flushHand_2);
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        assertEquals(HandsComparator.getHighestHandType(handsList), HandType.FOUR_OF_A_KIND);
    }

    @Test
    public void getHandsWithHighestHandTypeList_FourOfKind_Test() {
        handsList.clear();
        handsList.add(fourOfKindHand_1);
        handsList.add(fourOfKindHand_2);
        handsList.add(fullHouseHand_1);
        handsList.add(fullHouseHand_2);
        handsList.add(flushHand_1);
        handsList.add(flushHand_2);
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        outcomeHandsList.clear();
        outcomeHandsList.add(fourOfKindHand_1);
        outcomeHandsList.add(fourOfKindHand_2);
        assertEquals(HandsComparator.getHandsWithHighestHandTypeList(handsList), outcomeHandsList);
    }
    //endregion

    //region Full House
    @Test
    public void getHighestHandType_FullHouse_Test() {
        handsList.clear();
        handsList.add(fullHouseHand_1);
        handsList.add(fullHouseHand_2);
        handsList.add(flushHand_1);
        handsList.add(flushHand_2);
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        assertEquals(HandsComparator.getHighestHandType(handsList), HandType.FULL_HOUSE);
    }

    @Test
    public void getHandsWithHighestHandTypeList_FullHouse_Test() {
        handsList.clear();
        handsList.add(fullHouseHand_1);
        handsList.add(fullHouseHand_2);
        handsList.add(flushHand_1);
        handsList.add(flushHand_2);
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        outcomeHandsList.clear();
        outcomeHandsList.add(fullHouseHand_1);
        outcomeHandsList.add(fullHouseHand_2);
        assertEquals(HandsComparator.getHandsWithHighestHandTypeList(handsList), outcomeHandsList);
    }
    //endregion

    //region Flush
    @Test
    public void getHighestHandType_Flush_Test() {
        handsList.clear();
        handsList.add(flushHand_1);
        handsList.add(flushHand_2);
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        assertEquals(HandsComparator.getHighestHandType(handsList), HandType.FLUSH);
    }

    @Test
    public void getHandsWithHighestHandTypeList_Flush_Test() {
        handsList.clear();
        handsList.add(flushHand_1);
        handsList.add(flushHand_2);
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        outcomeHandsList.clear();
        outcomeHandsList.add(flushHand_1);
        outcomeHandsList.add(flushHand_2);
        assertEquals(HandsComparator.getHandsWithHighestHandTypeList(handsList), outcomeHandsList);
    }
    //endregion

    //region Straight
    @Test
    public void getHighestHandType_Straight_Test() {
        handsList.clear();
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        assertEquals(HandsComparator.getHighestHandType(handsList), HandType.STRAIGHT);
    }

    @Test
    public void getHandsWithHighestHandTypeList_Straight_Test() {
        handsList.clear();
        handsList.add(straightHand_1);
        handsList.add(straightHand_2);
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        outcomeHandsList.clear();
        outcomeHandsList.add(straightHand_1);
        outcomeHandsList.add(straightHand_2);
        assertEquals(HandsComparator.getHandsWithHighestHandTypeList(handsList), outcomeHandsList);
    }
    //endregion

    //region Three Of A Kind
    @Test
    public void getHighestHandType_ThreeOfKind_Test() {
        handsList.clear();
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        assertEquals(HandsComparator.getHighestHandType(handsList), HandType.THREE_OF_A_KIND);
    }

    @Test
    public void getHandsWithHighestHandTypeList_ThreeOfKind_Test() {
        handsList.clear();
        handsList.add(threeOfKindHand_1);
        handsList.add(threeOfKindHand_2);
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        outcomeHandsList.clear();
        outcomeHandsList.add(threeOfKindHand_1);
        outcomeHandsList.add(threeOfKindHand_2);
        assertEquals(HandsComparator.getHandsWithHighestHandTypeList(handsList), outcomeHandsList);
    }
    //endregion

    //region Two Pair
    @Test
    public void getHighestHandType_TwoPair_Test() {
        handsList.clear();
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        assertEquals(HandsComparator.getHighestHandType(handsList), HandType.TWO_PAIR);
    }

    @Test
    public void getHandsWithHighestHandTypeList_TwoPair_Test() {
        handsList.clear();
        handsList.add(twoPairHand_1);
        handsList.add(twoPairHand_2);
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        outcomeHandsList.clear();
        outcomeHandsList.add(twoPairHand_1);
        outcomeHandsList.add(twoPairHand_2);
        assertEquals(HandsComparator.getHandsWithHighestHandTypeList(handsList), outcomeHandsList);
    }
    //endregion

    //region Pair
    @Test
    public void getHighestHandType_OnePair_Test() {
        handsList.clear();
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        assertEquals(HandsComparator.getHighestHandType(handsList), HandType.ONE_PAIR);
    }

    @Test
    public void getHandsWithHighestHandTypeList_OnePair_Test() {
        handsList.clear();
        handsList.add(onePairHand_1);
        handsList.add(onePairHand_2);
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        outcomeHandsList.clear();
        outcomeHandsList.add(onePairHand_1);
        outcomeHandsList.add(onePairHand_2);
        assertEquals(HandsComparator.getHandsWithHighestHandTypeList(handsList), outcomeHandsList);
    }
    //endregion

    //region High Card
    @Test
    public void getHighestHandType_HighCard_Test() {
        handsList.clear();
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        assertEquals(HandsComparator.getHighestHandType(handsList), HandType.HIGH_CARD);
    }

    @Test
    public void getHandsWithHighestHandTypeList_HighCard_Test() {
        handsList.clear();
        handsList.add(highCardHand_1);
        handsList.add(highCardHand_2);

        outcomeHandsList.clear();
        outcomeHandsList.add(highCardHand_1);
        outcomeHandsList.add(highCardHand_2);
        assertEquals(HandsComparator.getHandsWithHighestHandTypeList(handsList), outcomeHandsList);
    }
    //endregion
}
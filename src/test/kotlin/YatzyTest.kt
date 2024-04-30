import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import kotlin.test.assertEquals

class YatzyTest {

    @ParameterizedTest
    @MethodSource("sumOfAllDice")
    fun chance_scores_sum_of_all_dice(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int, expected: Int) {
        assertEquals(expected, Yatzy(d1, d2, d3, d4, d5).chance())
    }

    @Test
    fun yatzy_scores_50() {
        assertEquals(50, Yatzy(4, 4, 4, 4, 4).yatzy())
        assertEquals(50, Yatzy(6, 6, 6, 6, 6).yatzy())
        assertEquals(0, Yatzy(6, 6, 6, 6, 3).yatzy())
    }

    @Test
    fun test_1s() {
        assertEquals(1, Yatzy(1, 2, 3, 4, 5).ones())
        assertEquals(2, Yatzy(1, 2, 1, 4, 5).ones())
        assertEquals(0, Yatzy(6, 2, 2, 4, 5).ones())
        assertEquals(4, Yatzy(1, 2, 1, 1, 1).ones())
    }

    @Test
    fun test_2s() {
        assertEquals(4, Yatzy(1, 2, 3, 2, 6).twos())
        assertEquals(10, Yatzy(2, 2, 2, 2, 2).twos())
    }

    @Test
    fun test_threes() {
        assertEquals(6, Yatzy(1, 2, 3, 2, 3).threes())
        assertEquals(12, Yatzy(2, 3, 3, 3, 3).threes())
    }

    @Test
    fun fours_test() {
        assertEquals(12, Yatzy(4, 4, 4, 5, 5).fours())
        assertEquals(8, Yatzy(4, 4, 5, 5, 5).fours())
        assertEquals(4, Yatzy(4, 5, 5, 5, 5).fours())
    }

    @Test
    fun fives() {
        assertEquals(10, Yatzy(4, 4, 4, 5, 5).fives())
        assertEquals(15, Yatzy(4, 4, 5, 5, 5).fives())
        assertEquals(20, Yatzy(4, 5, 5, 5, 5).fives())
    }

    @Test
    fun sixes_test() {
        assertEquals(0, Yatzy(4, 4, 4, 5, 5).sixes())
        assertEquals(6, Yatzy(4, 4, 6, 5, 5).sixes())
        assertEquals(18, Yatzy(6, 5, 6, 6, 5).sixes())
    }

    @Test
    fun one_pair() {
        assertEquals(6, Yatzy(3, 4, 3, 5, 6).onePair())
        assertEquals(6, Yatzy(1, 3, 3, 3, 1).onePair())
        assertEquals(10, Yatzy(5, 3, 3, 3, 5).onePair())
        assertEquals(12, Yatzy(5, 3, 6, 6, 5).onePair())
        assertEquals(0, Yatzy(3, 4, 1, 5, 6).onePair())
    }

    @Test
    fun two_Pair() {
        assertEquals(16, Yatzy(3, 3, 5, 4, 5).twoPair())
        assertEquals(16, Yatzy(3, 3, 5, 5, 5).twoPair())
        assertEquals(0, Yatzy(3, 3, 2, 4, 5).twoPair())
    }

    @Test
    fun three_of_a_kind() {
        assertEquals(9, Yatzy(3, 3, 3, 4, 5).threeOfAKind())
        assertEquals(15, Yatzy(5, 3, 5, 4, 5).threeOfAKind())
        assertEquals(9, Yatzy(3, 3, 3, 3, 5).threeOfAKind())
        assertEquals(0, Yatzy(3, 1, 3, 4, 5).threeOfAKind())
    }

    @Test
    fun four_of_a_knd() {
        assertEquals(12, Yatzy(3, 3, 3, 3, 5).fourOfAKind())
        assertEquals(20, Yatzy(5, 5, 5, 4, 5).fourOfAKind())
        assertEquals(8, Yatzy(2, 2, 2, 2, 2).fourOfAKind())
    }

    @Test
    fun smallStraight() {
        assertEquals(15, Yatzy(1, 2, 3, 4, 5).smallStraight())
        assertEquals(15, Yatzy(2, 3, 4, 5, 1).smallStraight())
        assertEquals(0, Yatzy(1, 2, 2, 4, 5).smallStraight())
        assertEquals(0, Yatzy(6, 1, 1, 2, 5).smallStraight())
    }

    @Test
    fun largeStraight() {
        assertEquals(20, Yatzy(6, 2, 3, 4, 5).largeStraight())
        assertEquals(20, Yatzy(2, 3, 4, 5, 6).largeStraight())
        assertEquals(0, Yatzy(1, 2, 2, 4, 5).largeStraight())
    }

    @Test
    fun fullHouse() {
        assertEquals(18, Yatzy(6, 2, 2, 2, 6).fullHouse())
        assertEquals(0, Yatzy(2, 3, 4, 5, 6).fullHouse())
    }

    companion object {
        @JvmStatic
        fun sumOfAllDice() = listOf(
            Arguments.of(2, 3, 4, 5, 1, 15),
            Arguments.of(3, 3, 4, 5, 1, 16)
        )
    }
}
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import kotlin.test.assertEquals

class YatzyTest {

    @ParameterizedTest
    @MethodSource("sumOfAllDice")
    fun chance_scores_sum_of_all_dice(yatzy: Yatzy, expected: Int) {
        assertEquals(expected, yatzy.chance())
    }

    @ParameterizedTest
    @MethodSource("yatzyScores50")
    fun yatzy_scores_50(yatzy: Yatzy, expected: Int) {
        assertEquals(expected, yatzy.yatzy())
    }

    @ParameterizedTest
    @MethodSource("test1S")
    fun test_1s(yatzy: Yatzy, expected: Int) {
        assertEquals(expected, yatzy.ones())
    }

    @ParameterizedTest
    @MethodSource("test2S")
    fun test_2s(yatzy: Yatzy, expected: Int) {
        assertEquals(expected, yatzy.twos())
    }

    @ParameterizedTest
    @MethodSource("testThrees")
    fun test_threes(yatzy: Yatzy, expected: Int) {
        assertEquals(expected, yatzy.threes())
    }

    @ParameterizedTest
    @MethodSource("foursTest")
    fun fours_test(yatzy: Yatzy, expected: Int) {
        assertEquals(expected, yatzy.fours())
    }

    @ParameterizedTest
    @MethodSource("fives")
    fun fives(yatzy: Yatzy, expected: Int) {
        assertEquals(expected, yatzy.fives())
    }

    @Test
    fun sixes_test() {
        assertEquals(0, Yatzy(4, 4, 4, 5, 5).sixes())
        assertEquals(6, Yatzy(4, 4, 6, 5, 5).sixes())
        assertEquals(18, Yatzy(6, 5, 6, 6, 5).sixes())
    }

    @ParameterizedTest
    @MethodSource("onePair")
    fun one_pair(yatzy: Yatzy, expected: Int) {
        assertEquals(expected, yatzy.onePair())
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

    @ParameterizedTest
    @MethodSource("smallStraight")
    fun smallStraight(yatzy: Yatzy, expected: Int) {
        assertEquals(expected, yatzy.smallStraight())
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
            Arguments.of(Yatzy(2, 3, 4, 5, 1), 15),
            Arguments.of(Yatzy(3, 3, 4, 5, 1), 16)
        )

        @JvmStatic
        fun yatzyScores50() = listOf(
            Arguments.of(Yatzy(4, 4, 4, 4, 4), 50),
            Arguments.of(Yatzy(6, 6, 6, 6, 6), 50),
            Arguments.of(Yatzy(6, 6, 6, 6, 3), 0)
        )

        @JvmStatic
        fun test1S() = listOf(
            Arguments.of(Yatzy(1, 2, 3, 4, 5), 1),
            Arguments.of(Yatzy(1, 2, 1, 4, 5), 2),
            Arguments.of(Yatzy(6, 2, 2, 4, 5), 0),
            Arguments.of(Yatzy(1, 2, 1, 1, 1), 4)
        )

        @JvmStatic
        fun test2S() = listOf(
            Arguments.of(Yatzy(1, 2, 3, 2, 6), 4),
            Arguments.of(Yatzy(2, 2, 2, 2, 2), 10)
        )

        @JvmStatic
        fun testThrees() = listOf(
            Arguments.of(Yatzy(1, 2, 3, 2, 3), 6),
            Arguments.of(Yatzy(2, 3, 3, 3, 3), 12)
        )

        @JvmStatic
        fun foursTest() = listOf(
            Arguments.of(Yatzy(4, 4, 4, 5, 5), 12),
            Arguments.of(Yatzy(4, 4, 5, 5, 5), 8),
            Arguments.of(Yatzy(4, 5, 5, 5, 5), 4)
        )

        @JvmStatic
        fun fives() = listOf(
            Arguments.of(Yatzy(4, 4, 4, 5, 5), 10),
            Arguments.of(Yatzy(4, 4, 5, 5, 5), 15),
            Arguments.of(Yatzy(4, 5, 5, 5, 5), 20)
        )

        @JvmStatic
        fun onePair() = listOf(
            Arguments.of(Yatzy(3, 4, 3, 5, 6), 6),
            Arguments.of(Yatzy(1, 3, 3, 3, 1), 6),
            Arguments.of(Yatzy(5, 3, 3, 3, 5), 10),
            Arguments.of(Yatzy(5, 3, 6, 6, 5), 12),
            Arguments.of(Yatzy(3, 4, 1, 5, 6), 0)
        )

        @JvmStatic
        fun smallStraight() = listOf(
            Arguments.of(Yatzy(1, 2, 3, 4, 5), 15),
            Arguments.of(Yatzy(2, 3, 4, 5, 1), 15),
            Arguments.of(Yatzy(1, 2, 2, 4, 5), 0),
            Arguments.of(Yatzy(6, 1, 1, 2, 5), 0),
        )
    }
}
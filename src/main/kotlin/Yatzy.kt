class Yatzy(d1: Int, d2: Int, d3: Int, d4: Int, _5: Int) {

    private var dice: IntArray = IntArray(5)

    init {
        dice[0] = d1
        dice[1] = d2
        dice[2] = d3
        dice[3] = d4
        dice[4] = _5
    }

    fun fours() = sumOf(dice.take(5), DICE.FOUR.number)

    fun fives() = sumOf(dice.toList(), DICE.FIVE.number)

    fun sixes() = sumOf(dice.toList(), DICE.SIX.number)

    companion object {
        fun sumOf(dice: List<Int>, placedOn: Int) = dice.filter { it == placedOn }.sumOf { placedOn }
        fun chance(vararg diceNumbers: Int) = diceNumbers.sum()
        fun yatzy(vararg dice: Int) = if (dice.distinct().size == 1) 50 else 0
        fun ones(vararg diceNumbers: Int) = sumOf(diceNumbers.toList(), DICE.ONE.number)
        fun twos(vararg diceNumbers: Int) = sumOf(diceNumbers.toList(), DICE.TWO.number)
        fun threes(vararg diceNumbers: Int) = sumOf(diceNumbers.toList(), DICE.THREE.number)
        fun onePair(vararg diceNumbers: Int) =
            diceNumbers.toList()
                .groupingBy { it }
                .eachCount().filter { it.value >= 2 }
                .takeIf { it.isNotEmpty() }?.let { groupingValues ->
                    groupingValues.map { it.key }.max() * 2
                } ?: 0

        fun twoPair(vararg diceNumbers: Int) =
            diceNumbers.toList()
                .groupingBy { it }
                .eachCount()
                .filter { it.value >= 2 }
                .takeIf { it.isNotEmpty() && it.size >= 2 }?.let { groupingValues ->
                    groupingValues.map { it.key }.sum() * 2
                } ?: 0

        fun threeOfAKind(vararg diceNumbers: Int) =
            diceNumbers.toList().pairsSum(3)

        fun fourOfAKind(vararg diceNumbers: Int) =
            diceNumbers.toList().pairsSum(4)

        private fun List<Int>.pairsSum(pairNumber: Int) =
            groupingBy { it }
                .eachCount()
                .filter { it.value >= pairNumber }
                .takeIf { it.isNotEmpty() }?.let { groupingValues ->
                    groupingValues.map { it.key }.sum() * pairNumber
                } ?: 0

        fun smallStraight(vararg diceNumbers: Int) = diceNumbers.toList().straightSum(15)
        fun largeStraight(vararg diceNumbers: Int) = diceNumbers.toList().straightSum(20)

        private fun List<Int>.straightSum(sumOfAllDiceExpected: Int) = takeIf { sum() == sumOfAllDiceExpected && toList().isDistinct()}?.let { sumOfAllDiceExpected } ?: 0
        private fun List<Int>.isDistinct() = distinct().size == size

        fun fullHouse(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val tallies: IntArray
            var _2 = false
            var i: Int
            var _2_at = 0
            var _3 = false
            var _3_at = 0

            tallies = IntArray(6)
            tallies[d1 - 1] += 1
            tallies[d2 - 1] += 1
            tallies[d3 - 1] += 1
            tallies[d4 - 1] += 1
            tallies[d5 - 1] += 1

            i = 0
            while (i != 6) {
                if (tallies[i] == 2) {
                    _2 = true
                    _2_at = i + 1
                }
                i += 1
            }

            i = 0
            while (i != 6) {
                if (tallies[i] == 3) {
                    _3 = true
                    _3_at = i + 1
                }
                i += 1
            }

            return if (_2 && _3)
                _2_at * 2 + _3_at * 3
            else
                0
        }
    }
}

enum class DICE(val number: Int) {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);
}
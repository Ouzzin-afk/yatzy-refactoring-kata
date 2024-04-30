class Yatzy(vararg diceNumbers: Int) {
    private var dice = diceNumbers

    fun ones() = sumOf(dice.toList(), DICE.ONE.number)
    fun twos() = sumOf(dice.toList(), DICE.TWO.number)
    fun threes() = sumOf(dice.toList(), DICE.THREE.number)
    fun fours() = sumOf(dice.take(5), DICE.FOUR.number)
    fun fives() = sumOf(dice.toList(), DICE.FIVE.number)
    fun sixes() = sumOf(dice.toList(), DICE.SIX.number)

    private fun sumOf(dice: List<Int>, placedOn: Int) = dice.filter { it == placedOn }.sumOf { placedOn }

    fun chance() = dice.sum()
    fun yatzy() = if (dice.distinct().size == 1) 50 else 0
    fun onePair() =
        dice.toList()
            .groupingBy { it }
            .eachCount().filter { it.value >= 2 }
            .takeIf { it.isNotEmpty() }?.let { groupingValues ->
                groupingValues.map { it.key }.max() * 2
            } ?: 0

    fun twoPair() =
        dice.toList()
            .groupingBy { it }
            .eachCount()
            .filter { it.value >= 2 }
            .takeIf { it.isNotEmpty() && it.size >= 2 }?.let { groupingValues ->
                groupingValues.map { it.key }.sum() * 2
            } ?: 0

    fun threeOfAKind() =
        dice.toList().pairsSum(3)

    fun fourOfAKind() =
        dice.toList().pairsSum(4)

    private fun List<Int>.pairsSum(pairNumber: Int) =
        groupingBy { it }
            .eachCount()
            .filter { it.value >= pairNumber }
            .takeIf { it.isNotEmpty() }?.let { groupingValues ->
                groupingValues.map { it.key }.sum() * pairNumber
            } ?: 0

    fun smallStraight() = dice.toList().straightSum(15)
    fun largeStraight() = dice.toList().straightSum(20)

    private fun List<Int>.straightSum(sumOfAllDiceExpected: Int) = takeIf { sum() == sumOfAllDiceExpected && toList().isDistinct()}?.let { sumOfAllDiceExpected } ?: 0
    private fun List<Int>.isDistinct() = distinct().size == size

    fun fullHouse() =
        dice.toList()
            .groupingBy { it }
            .eachCount()
            .takeIf { it.size == 2 && it.values.contains(3) }
            ?.let { dice.sum() } ?: 0;

}

enum class DICE(val number: Int) {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);
}
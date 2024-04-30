class Yatzy(vararg diceNumbers: Int) {
    private var dice = diceNumbers

    fun ones() = dice.toList().sumOf(DICE.ONE.number)
    fun twos() = dice.toList().sumOf(DICE.TWO.number)
    fun threes() = dice.toList().sumOf(DICE.THREE.number)
    fun fours() = dice.toList().sumOf(DICE.FOUR.number)
    fun fives() = dice.toList().sumOf(DICE.FIVE.number)
    fun sixes() = dice.toList().sumOf(DICE.SIX.number)

    fun chance() = dice.sum()
    fun yatzy() = if (dice.distinct().size == 1) 50 else 0
    fun onePair() =
        dice.toList()
            .groupingBy(2)
            ?.let { groupingValues ->
                groupingValues.map { it.key }.max() * 2
            } ?: 0

    fun twoPair() =
        dice.toList()
            .groupingBy(2)
            ?.takeIf { it.size >= 2 }
            ?.let { groupingValues ->
                groupingValues.map { it.key }.sum() * 2
            } ?: 0

    fun threeOfAKind() =
        dice.toList().pairsSum(3)

    fun fourOfAKind() =
        dice.toList().pairsSum(4)

    fun smallStraight() = dice.toList().straightSum(15)
    fun largeStraight() = dice.toList().straightSum(20)

    fun fullHouse() =
        dice.toList()
            .groupingBy { it }
            .eachCount()
            .takeIf { it.size == 2 && it.values.contains(3) }
            ?.let { dice.sum() } ?: 0;

    private fun List<Int>.sumOf(placedOn: Int) = filter { it == placedOn }.sumOf { placedOn }

    private fun List<Int>.groupingBy(pairNumber: Int) = groupingBy { it }
        .eachCount()
        .filter { it.value >= pairNumber }
        .takeIf { it.isNotEmpty() }

    private fun List<Int>.isDistinct() = distinct().size == size

    private fun List<Int>.pairsSum(pairNumber: Int) =
        groupingBy(pairNumber)
            ?.let { groupingValues ->
                groupingValues.map { it.key }.sum() * pairNumber
            } ?: 0

    private fun List<Int>.straightSum(sumOfAllDiceExpected: Int) =
        takeIf { sum() == sumOfAllDiceExpected && toList().isDistinct() }?.let { sumOfAllDiceExpected } ?: 0

}

enum class DICE(val number: Int) {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);
}
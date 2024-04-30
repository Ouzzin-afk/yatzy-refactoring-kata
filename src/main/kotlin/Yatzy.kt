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
        fun chance(vararg diceNumbers: Int)= diceNumbers.sum()
        fun yatzy(vararg dice: Int)= if(dice.distinct().size == 1) 50 else 0
        fun ones(vararg diceNumbers: Int)= sumOf(diceNumbers.toList(), DICE.ONE.number)
        fun twos(vararg diceNumbers: Int)= sumOf(diceNumbers.toList(), DICE.TWO.number)
        fun threes(vararg diceNumbers: Int)= sumOf(diceNumbers.toList(), DICE.THREE.number)

        fun score_pair(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val counts = IntArray(6)
            counts[d1 - 1]++
            counts[d2 - 1]++
            counts[d3 - 1]++
            counts[d4 - 1]++
            counts[d5 - 1]++
            var at: Int
            at = 0
            while (at != 6) {
                if (counts[6 - at - 1] >= 2)
                    return (6 - at) * 2
                at++
            }
            return 0
        }

        fun two_pair(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val counts = IntArray(6)
            counts[d1 - 1]++
            counts[d2 - 1]++
            counts[d3 - 1]++
            counts[d4 - 1]++
            counts[d5 - 1]++
            var n = 0
            var score = 0
            var i = 0
            while (i < 6) {
                if (counts[6 - i - 1] >= 2) {
                    n++
                    score += 6 - i
                }
                i += 1
            }
            return if (n == 2)
                score * 2
            else
                0
        }

        fun four_of_a_kind(_1: Int, _2: Int, d3: Int, d4: Int, d5: Int): Int {
            val tallies: IntArray = IntArray(6)
            tallies[_1 - 1]++
            tallies[_2 - 1]++
            tallies[d3 - 1]++
            tallies[d4 - 1]++
            tallies[d5 - 1]++
            for (i in 0..5)
                if (tallies[i] >= 4)
                    return (i + 1) * 4
            return 0
        }

        fun three_of_a_kind(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val t: IntArray = IntArray(6)
            t[d1 - 1]++
            t[d2 - 1]++
            t[d3 - 1]++
            t[d4 - 1]++
            t[d5 - 1]++
            for (i in 0..5)
                if (t[i] >= 3)
                    return (i + 1) * 3
            return 0
        }

        fun smallStraight(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val tallies: IntArray = IntArray(6)
            tallies[d1 - 1] += 1
            tallies[d2 - 1] += 1
            tallies[d3 - 1] += 1
            tallies[d4 - 1] += 1
            tallies[d5 - 1] += 1
            return if (tallies[0] == 1 &&
                tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
            ) 15 else 0
        }

        fun largeStraight(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int): Int {
            val tallies: IntArray = IntArray(6)
            tallies[d1 - 1] += 1
            tallies[d2 - 1] += 1
            tallies[d3 - 1] += 1
            tallies[d4 - 1] += 1
            tallies[d5 - 1] += 1
            return if (tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
                && tallies[5] == 1
            ) 20 else 0
        }

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
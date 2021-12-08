import java.lang.Math.abs

class CrabGroup(val initalPosition: List<Int>) : SolutionExecutor {
    fun median(): Int {
        val array = initalPosition.sorted()
        if (array.size % 2 == 0) {
            return ((array[array.size / 2] + array[array.size / 2 - 1]) / 2)
        } else {
            return (array[array.size / 2])
        }
    }

    fun optimalFuelUsage(): Int {
        //val bestPosition = median()
        val bestPosition = bruteForceBestSolution()
        return initalPosition
            .map { moveCost(bestPosition - it) }
            .sum()
    }

    private fun bruteForceBestSolution(): Int {
        val sortedPosition = initalPosition.sorted()
        return (sortedPosition.first()..sortedPosition.last())
            .map { Pair(it, costToPosition(it))} // calculate all cost to each position
            .sortedBy { it.second } // sort by cost
            .first().first // take the position with lowest cost
    }

    private fun costToPosition(position: Int) =
        initalPosition.map { moveCost(position - it) }.sum()

    fun moveCost(move: Int): Int {
        return (1..abs(move)).fold(0) { i, j -> i + j }
    }


    companion object {
        fun from(input: List<String>): CrabGroup {
            return CrabGroup(input[0].split(",").map { it.toInt() })
        }
    }

    override fun process(input: List<String>): Number {
        return CrabGroup.from(input).optimalFuelUsage()
    }
}
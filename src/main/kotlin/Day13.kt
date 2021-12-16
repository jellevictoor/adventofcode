class Day13 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        var from = Paper.from(
            input
                .filter { it.contains(",") }
                .map { Pair(it.split(",")[0].toInt(), it.split(",")[1].toInt()) })
        val folds = input
            .filter { it.contains("fold along ") }
            .map { it.split("fold along ") }
            .map { it[1] }
        folds
            .forEach {
                val instruction = it.split("=")[0].uppercase()
                val position = it.split("=")[1].toInt()
                from = from.fold(Paper.Axis.valueOf(instruction), position)

            }
        println(from)
        return from.getDots()
    }

}

data class Paper(val state: List<Dot>) {
    fun fold(axis: Axis, position: Int): Paper {
        return when (axis) {
            Axis.X -> foldX(position)
            Axis.Y -> foldY(position)
        }
    }

    enum class Axis {
        X,
        Y,
    }

    fun foldX(position: Int): Paper {
        val newState = (state.filter { it.x > position }
            .map { Dot(position - (it.x - position), it.y) }
                + state.filter { it.x < position })

        return Paper(newState.distinct())
    }

    fun foldY(position: Int): Paper {
        val newState = (state
            .filter { it.y > position }
            .map { Dot(it.x, position - (it.y - position)) }
                + state.filter { it.y < position })
        return Paper(newState.distinct())
    }

    fun getDots(): Int {
        return state.size
    }

    override fun toString(): String {
        val maxX = state.maxOf { it.x }
        val maxY = state.maxOf { it.y }
        val grid = Array(maxY + 1) { CharArray(maxX + 1) }
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                grid[i][j] = ' '
            }
        }
        state.forEach { grid[it.y][it.x] = '*' }
        return grid
            .map { it.joinToString(" ") }
            .joinToString("\n")
    }

    companion object {
        fun from(state: List<Pair<Int, Int>>): Paper {
            return Paper(state.map { Dot(it.first, it.second) })
        }
    }
}

data class Dot(val x: Int, val y: Int)
class BingoSolver() : SolutionExecutor {
    override fun process(input: List<String>): Int {
        val draw = input.get(0)
        val boards = ArrayList<Board>()
        var boardInput = ArrayList<String>()
        for (line in input.subList(2, input.size)) {
            if (line.isNotEmpty()) {
                boardInput.add(line)
            } else {
                boards.add(Board.of(boardInput))
                boardInput = ArrayList()
            }
        }
        val result = play(draw.split(",").map { it.toInt() }, boards)
        return result
    }

}


data class Board(private val values: List<List<Cell>>) {

    fun calculateScore(): Int {
        return values
            .map { it -> it.filter { it.state == State.NOT_FOUND } }
            .map { it -> it.map { it.value } }
            .flatten()
            .sum()

    }

    fun hasWon(): Boolean {
        val hasWoneHorizontal = validateRows(values)
        val hasWonVertical = validateRows(transpose(values))
        return hasWoneHorizontal || hasWonVertical
    }

    private fun validateRows(values: List<List<Cell>>): Boolean {
        val completeRows = values.filter { it ->
            it.count { it.state == State.FOUND } == BOARD_SIZE
        }
        return completeRows.isNotEmpty()
    }

    fun updateBoard(value: Int) {
        values
            .forEach { it ->
                it.filter { it.value == value }
                    .forEach { it.state = State.FOUND }
            }
    }

    companion object {
        const val BOARD_SIZE = 5
        fun of(values: List<String>): Board {
            val cells = values
                .map { it.trim() }
                .map { it.replace("  ", " ") }
                .map { it.split(" ") }
                .map { it.map { it.toInt() } }
                .map { it.map { Cell.newCell(it) } }
            return Board(cells)
        }
    }

    override fun toString(): String {
        return values.joinToString { it.joinToString { it.toString() + "\t" } + "\n" }
    }
}

private fun transpose(metrics: List<List<Cell>>): List<List<Cell>> {
    val transpose =
        Array(metrics[0].size) {
            Array(
                metrics.size
            ) { Cell(0, State.NOT_FOUND) }
        }

    for (i in metrics.indices) {
        for (j in 0 until metrics[0].size) {
            transpose[j][i] = metrics[i][j]
        }
    }
    return transpose.map { it.toList() }.toList()
}


data class Cell(val value: Int, var state: State) {
    companion object {
        fun newCell(value: Int): Cell {
            return Cell(value, State.NOT_FOUND)
        }
    }

    override fun toString(): String {
        return "" + value + if (state == State.FOUND) "*" else " "
    }
}

enum class State {
    NOT_FOUND,
    FOUND,
}

fun play(input: List<Int>, boards: List<Board>): Int {
    var deductedBoards = boards;
    var lastWinningBoard = boards.get(0)
    for (value in input) {
        deductedBoards.forEach { it.updateBoard(value) }
        val winningBoards = evaluateBoards(deductedBoards)
        if (winningBoards.isNotEmpty()) {
            lastWinningBoard=winningBoards[0]
            deductedBoards =
                deductedBoards.filter { it -> !winningBoards.contains(it) }
            println(""+deductedBoards.size + " roll " + value)
            if(deductedBoards.size==0) {
                return lastWinningBoard.calculateScore() * value
            }
        }
        boards.forEach { println(it) }
    }
    return 0
}

fun evaluateBoards(boards: List<Board>): List<Board> {
    return boards.filter { it.hasWon() }
}


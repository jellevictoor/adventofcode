package io.victoor.aco2021

class EncodingRunner : SolutionExecutor {
    override fun process(input: List<String>): Number {
        val scores = input
            .map { Line(it) }
            .filter { it.isValid() == 0 }
            .map { it.completeLine() }
            .sorted()
        println(scores)
        return median(scores)
    }

    fun median(array: List<Long>): Long {
        if (array.size % 2 == 0) {
            return ((array[array.size / 2] + array[array.size / 2 - 1]) / 2)
        } else {
            return (array[array.size / 2])
        }
    }
}

class Line(val input: String) {
    fun isValid(): Int {
        val queue = mutableListOf<Char>()
        for (char in input.toCharArray()) {
            when (char) {
                '(' -> queue.add(char)
                '[' -> queue.add(char)
                '{' -> queue.add(char)
                '<' -> queue.add(char)
                ')' -> if (found(queue, char)) {
                    queue.removeLast()
                } else {
                    return 3
                }
                ']' -> if (found(queue, char)) {
                    queue.removeLast()
                } else {
                    return 57
                }
                '>' ->
                    if (found(queue, char)) {
                        queue.removeLast()
                    } else {
                        return 25137
                    }
                '}' -> if (found(queue, char)) {
                    queue.removeLast()
                } else {
                    return 1197
                }
            }
        }
        return 0
    }

    fun completeLine(): Long {
        val queue = mutableListOf<Char>()
        for (char in input.toCharArray()) {
            when (char) {
                '(' -> queue.add(char)
                '[' -> queue.add(char)
                '{' -> queue.add(char)
                '<' -> queue.add(char)
                ')' -> queue.removeLast()
                ']' -> queue.removeLast()
                '>' -> queue.removeLast()
                '}' -> queue.removeLast()
            }
        }
        return queue.reversed().map { findByClosingChar(it) }.map { it.score }.fold(0L) { acc, i -> acc * 5L + i }
    }

    private fun found(queue: MutableList<Char>, char: Char): Boolean {
        return queue.last() == findOpening(char)
    }

    private fun findOpening(char: Char): Char {
        return findMatching(char).opening
    }

    private fun findByClosingChar(char: Char): CompaningChar {
        return findMatching(char)
    }
}
data class CompaningChar(val opening: Char, val closing: Char, val score: Int) {
    companion object {
        fun fromChar(char: Char): CompaningChar = findMatching(char)
    }
}

val ROUND = CompaningChar('(', ')', 1)
val SQUARE = CompaningChar('[', ']', 2)
val CURLY = CompaningChar('{', '}', 3)
val TRIANGLE = CompaningChar('<', '>', 4)
val companionChars: List<CompaningChar> = listOf(ROUND, SQUARE, CURLY, TRIANGLE)
fun findMatching(c: Char): CompaningChar = companionChars.first() { it.opening == c || it.closing == c }

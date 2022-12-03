package io.victoor.aco2022

import io.victoor.aoc.SolutionExecutor

class Day3 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        return input.map { processContent(it) }.sum()
    }

    fun processContent(it: String): Int {
        val first = it.substring(0, it.length / 2)
        val second = it.substring(it.length / 2)
        val firstUniqueChars = first.toSet()
        val secondUniqueChars = second.toSet()

        return listOf(firstUniqueChars.toList(), secondUniqueChars.toList())
            .flatten()
            .groupingBy { it }
            .eachCount()
            .filter { it.value > 1 }
            .map { it.key }.sumOf {
                getValueOfChar(it)
            }

    }

    private fun getValueOfChar(it: Char) = if (it.isUpperCase()) {
        it.code - 64 + 26 // the upper case chars come after the lowercase
    } else {
        it.code - 96
    }

}

package io.victoor.aco2022

import io.victoor.aoc.SolutionExecutor

class Day3 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        return input.chunked(3).map { processContent(it) }.sum()
    }

    fun processContent(team: List<String>): Int {
        return team
            .map { it.toSet() }// only unique per team
            .flatten() // join all char for the whole team
            .groupingBy { it }.eachCount() // count the char occurence
            .filter { it.value == 3 }
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

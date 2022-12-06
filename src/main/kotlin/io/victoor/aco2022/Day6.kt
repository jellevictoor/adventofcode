package io.victoor.aco2022

import io.victoor.aoc.SolutionExecutor

class Day6 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        val packageSize = 14
        return firstFirstOccurred(input, packageSize)
    }

    public fun firstFirstOccurred(input: List<String>, packageSize: Int): Int {
        val startPackage = input.first().windowed(packageSize).first { it.toSet().size == packageSize }
        return input.first().indexOf(startPackage) + startPackage.length
    }
}

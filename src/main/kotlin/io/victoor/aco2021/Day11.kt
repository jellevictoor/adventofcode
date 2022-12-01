package io.victoor.aco2021

import io.victoor.aoc.SolutionExecutor

class Day11() : SolutionExecutor {
    override fun process(input: List<String>): Number {
        val map = input
            .map { it.toCharArray().map { Character.getNumericValue(it) } }
        val cave = Cave.fromStartSituation(map)
        var day = 0
        while (!cave.allFlashing()) {
            cave.moveDay(1)
            day++
        }
        return day

    }

}

data class DumboOctopus(val x: Int, val y: Int, var charge: Int) {
    var flashExecuted = false
    fun increaseCharge(): DumboOctopus {
        charge++
        return this
    }

    fun isFlashed() = charge > 9
    fun executedFlash() {
        flashExecuted = true
    }

    fun resetFlash() {
        if (charge > 9) {
            charge = 0
        }
        flashExecuted = false
    }
}

class Cave(val day: Int, var flashes: Int, val octopusses: List<DumboOctopus>) {
    private val maxX = octopusses.maxOf { it.x }
    private val minX = octopusses.minOf { it.x }
    private val maxY = octopusses.maxOf { it.y }
    private val minY = octopusses.minOf { it.y }

    data class Point(val x: Int, val y: Int)

    fun lookup(x: Int, y: Int): DumboOctopus {
        return octopusses.first { it.x == x && it.y == y }
    }

    companion object {
        fun fromStartSituation(startPosition: List<List<Int>>): Cave {
            val octopuses = mutableListOf<DumboOctopus>()
            for (i in startPosition.indices) {
                for (j in startPosition[i].indices) {
                    octopuses.add(DumboOctopus(i, j, startPosition[i][j]))
                }
            }
            return Cave(0, 0, octopuses)
        }
    }

    fun moveDay(day: Int = 1): Cave {
        val cave: Cave = this
        for (i in 1..day) {
            cave.octopusses
                .forEach { it.increaseCharge() }
            oneDayFlash(cave)
            cave.flashes += cave.octopusses.count { it.isFlashed() }
            cave.octopusses.forEach { it.resetFlash() }
        }
        return cave
    }

    fun allFlashing(): Boolean {
        return octopusses.all { it.charge == 0 }
    }

    private fun oneDayFlash(cave: Cave) {
        val newFlashed = cave.octopusses
            .filter { it.isFlashed() && !it.flashExecuted }
            .map {
                it.flashExecuted = true
                it
            }
            .map { cave.findAdjecent(it).map { it.increaseCharge() }.toSet() }
            .flatten()
            .filter { it.isFlashed() && !it.flashExecuted }

        if (newFlashed.isNotEmpty()) {
            oneDayFlash(cave)
        }
    }

    private fun findFlashedOctupusses(): List<DumboOctopus> {
        return octopusses
            .filter { it.isFlashed() }
    }

    fun findAdjecent(point: DumboOctopus): List<DumboOctopus> {
        return listOf(
            Point(point.x - 1, point.y - 1),
            Point(point.x, point.y - 1),
            Point(point.x + 1, point.y - 1),
            Point(point.x - 1, point.y),
            Point(point.x + 1, point.y),
            Point(point.x - 1, point.y + 1),
            Point(point.x, point.y + 1),
            Point(point.x + 1, point.y + 1)
        ).filter { it.x >= minX && it.y >= minY && it.x <= maxX && it.y <= maxY }
            .map { lookup(it.x, it.y) }
    }

    override fun toString(): String {
        val grid = Array(maxY + 1) { IntArray(maxX + 1) }
        octopusses
            .forEach { grid[it.x][it.y] = it.charge }
        return "CAVE:\n" +
                grid.map { it.joinToString(" ") }
                    .joinToString("\n") + "\n-----------------"
    }
}

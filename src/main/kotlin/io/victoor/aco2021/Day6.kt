package io.victoor.aco2021

import io.victoor.aoc.SolutionExecutor

data class Lanternfish(val age: Int)

class School(val age: Int, val population: Long) {
    fun live(): List<School> {
        if (age ==  0) {
            return listOf(School(6, population), School(8, population))
        } else {
            return listOf(School(age - 1, population))
        }
    }
}

class SchoolSimulator(
    val initalPopulation: List<Lanternfish>,
    val daysToSimulate: Int
) : SolutionExecutor {
    fun calculatePopulation(): Long {
        val simulate = simulate(initalPopulation)
        return simulate
    }

    private fun simulate(_population: List<Lanternfish>): Long {
        val population = _population
            .groupBy { it.age }
            .map { School(it.key, it.value.size.toLong()) }
        var newPopulation = population
        for (i in 1..daysToSimulate) {
            val flattenedSchool = newPopulation
                .map { it.live() }
                .flatten()
            newPopulation = flattenedSchool
                .groupBy { it.age }
                .map { School(it.key, it.value.sumOf { it.population }) }
        }
        val sumOf = newPopulation.sumOf { it.population }
        return sumOf
    }

    override fun process(input: List<String>): Long {
        val initialPopulation = input
            .map { it.split(",") }
            .flatten()
            .map { it.toInt() }
            .map { Lanternfish(it) }
        return SchoolSimulator(initialPopulation, daysToSimulate).calculatePopulation()
    }

}


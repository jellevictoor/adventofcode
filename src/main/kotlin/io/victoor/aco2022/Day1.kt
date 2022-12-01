package io.victoor.aco2022

import io.victoor.aoc.SolutionExecutor

class Day1 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        return totalCalories(input,3)
    }

     fun totalCalories(input: List<String>,numberOfElves: Int) =
        findElfWithMostCalories(input,numberOfElves).map { it.totalCalories() }.reduce { a, b -> a + b }

}
fun findElfWithMostCalories(input: List<String>,numberOfElves:Int = 1): List<Elf> {
    val elves = getElves(input)
    return elves.sortedBy { elem -> elem.totalCalories() }.reversed().subList(0,numberOfElves)
}

private fun getElves(input: List<String>): MutableList<Elf> {
    var calories = mutableListOf<Int>()
    val elves = mutableListOf<Elf>()
    for (elem in input) {
        if (elem == "") {
            elves.add(Elf(calories))
            calories = mutableListOf()
        } else {
            calories.add(Integer.valueOf(elem))
        }
    }
    elves.add(Elf(calories))

    return elves
}

data class Elf(val food: List<Int>) {
    fun totalCalories(): Int {
        return food.reduce { a, b -> a + b }
    }
}
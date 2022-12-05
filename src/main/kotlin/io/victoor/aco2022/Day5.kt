package io.victoor.aco2022

import io.victoor.aoc.SolutionExecutor
import java.util.*

class Day5 : SolutionExecutor {
    override fun process(input: List<String>): Int {
        val startSituation = parseStartSituation(input.subList(0, input.indexOf("")))
        startSituation.positions.forEach{println(it)}
        val instructions = parseInstructions(input.subList(input.indexOf("") + 1, input.size))
        startSituation.excute(instructions)
        println(startSituation.finalPosition())
        return 0
    }

    private fun parseInstructions(subList: List<String>): List<Move> {
        //move 1 from 2 to 1
        val regex = "move (\\d{1,3}) from (\\d{1,3}) to (\\d{1,3})".toRegex()
        return subList.map { it ->

            val matchResult = regex.find(it)

            val qty = matchResult!!.groups[1]!!.value.toInt()
            val from = matchResult!!.groups[2]!!.value.toInt()
            val to = matchResult!!.groups[3]!!.value.toInt()
            Move(qty, from, to)
        }
    }

}

data class Move(val qty: Int, val from: Int, val to: Int)

fun parseStartSituation(startSituation: List<String>): Dock {
    val map: List<List<String>> = startSituation
        .map { it.chunked(4).map { it.trim() } }
    val transpose = transpose(map)
    return Dock(transpose.map { it -> LinkedList(it) }.toMutableList())
}

fun transpose(input: List<List<String>>): MutableList<MutableList<String>> {
    val transpose = Array(input.get(0).size) { Array<String>(input.size) { _ -> "" } }

    for (i in input.indices) {
        for (j in 0 until input[0].size) {
            transpose[j][i] = input[i][j]
        }
    }
    return transpose.map { it.toList().filter { it != "" }.toMutableList() }.toMutableList()
}


data class Dock(val positions: List<LinkedList<String>>) {
    fun excute(instructions: List<Move>) {
        for (instruction in instructions) {
            val fromStack = positions[instruction.from - 1]
            val toStack = positions[instruction.to - 1]
            val itemsToMove = mutableListOf<String>()
            for (i in 1 .. instruction.qty) {
                itemsToMove.add(fromStack.pop())
            }
            for(itemToAdd in itemsToMove.reversed()) {
                toStack.offerFirst(itemToAdd)
            }
        }
    }

    fun finalPosition(): String {
        return positions.map { it.first() }.filter{it.contains("[")}.map { it.substring(1,2) }.joinToString("")
    }
}
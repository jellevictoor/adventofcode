package io.victoor.aco2022

import io.victoor.aoc.SolutionExecutor
import org.w3c.dom.ranges.Range

class Day4 : SolutionExecutor {
    override fun process(input: List<String>): Number {
       return input
            .map { toShifts(it) }
            .filter { ranges -> val intersect = ranges.get(0).intersect(ranges.get(1))

                //if (ranges.filter { it.toSet() == intersect }.isNotEmpty()) true else false
                intersect.isNotEmpty()
            }
            .count()
    }

    private fun toShifts(it: String): List<IntRange> {
        return it.split(",")
            .map { shift ->
                val split = shift.split("-")
                IntRange(split.get(0).toInt(), split.get(1).toInt())
            }.toList()
    }

}

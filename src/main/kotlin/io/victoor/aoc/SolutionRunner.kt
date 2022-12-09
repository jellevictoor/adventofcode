package io.victoor.aoc

import io.victoor.aco2022.*


fun run(executor: SolutionExecutor, inputFile: String): Number {
    return FileProcessor(inputFile, executor).count()
}

fun main() {
    val start = System.currentTimeMillis()
    /*
    println("day 1")
    println(io.victoor.aoc.run(io.victoor.aco2021.Counter(), "input-day1"))
    println(io.victoor.aoc.run(io.victoor.aco2021.SlidingCounter(), "input-day1"))
    println("day 2")
    println(io.victoor.aoc.run(io.victoor.aco2021.Navigator(),"input-day2"))
    println("day 3")
    println(io.victoor.aoc.run(io.victoor.aco2021.PowerConsumptionCalculator(),"input-day3"))
    println(io.victoor.aoc.run(io.victoor.aco2021.LifeSupportCalculator(),"input-day3"))
    println("day 4")
    println(io.victoor.aoc.run(io.victoor.aco2021.BingoSolver(),"input-day4"))
    println("day 5")
    println(io.victoor.aoc.run(io.victoor.aco2021.MapSolver(), "input-day5"))
    println(io.victoor.aoc.run(io.victoor.aco2021.SchoolSimulator(listOf(),256), "input-day6"))
    println(io.victoor.aoc.run(io.victoor.aco2021.CrabGroup(listOf()), "input-day7"))
    println(io.victoor.aoc.run(io.victoor.aco2021.Decoder(), "input-day8"))
    println(io.victoor.aoc.run(io.victoor.aco2021.HeightMapSeeker(), "input-day9"))
    println(io.victoor.aoc.run(io.victoor.aco2021.EncodingRunner(), "input-day10"))
    println(io.victoor.aoc.run(io.victoor.aco2021.Day11(), "input-day11"))
    println(io.victoor.aoc.run(io.victoor.aco2021.Day12(), "input-day12"))

    println(io.victoor.aoc.run(io.victoor.aco2021.Day13(), "input-day13"))

    println(io.victoor.aoc.run(io.victoor.aco2021.Day14(), "input-day14"))



    println(io.victoor.aoc.run(io.victoor.aco2021.Day15(), "input-day15"))
    println(run(Day16(), "input-day16"))
    println(run(Day17(), "input-day17"))
    println(run(Day21(), "input-day20"))
 */
    val time = System.currentTimeMillis() - start
    println(run(Day7(), "aco2022/input-day7"))
    println("took $time")
}
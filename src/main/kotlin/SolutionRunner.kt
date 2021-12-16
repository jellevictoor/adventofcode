fun run(executor: SolutionExecutor, inputFile: String): Number {
    return FileProcessor(inputFile, executor).count()
}

fun main() {
    val start = System.currentTimeMillis()
    /*
    println("day 1")
    println(run(Counter(), "input-day1"))
    println(run(SlidingCounter(), "input-day1"))
    println("day 2")
    println(run(Navigator(),"input-day2"))
    println("day 3")
    println(run(PowerConsumptionCalculator(),"input-day3"))
    println(run(LifeSupportCalculator(),"input-day3"))
    println("day 4")
    println(run(BingoSolver(),"input-day4"))
    println("day 5")
    println(run(MapSolver(), "input-day5"))
    println(run(SchoolSimulator(listOf(),256), "input-day6"))
    println(run(CrabGroup(listOf()), "input-day7"))
    println(run(Decoder(), "input-day8"))
    println(run(HeightMapSeeker(), "input-day9"))
    println(run(EncodingRunner(), "input-day10"))
    println(run(Day11(), "input-day11"))
    println(run(Day12(), "input-day12"))

    println(run(Day13(), "input-day13"))

    println(run(Day14(), "input-day14"))



    println(run(Day15(), "input-day15"))
 */
    val time = System.currentTimeMillis() - start
    println("took $time")
}
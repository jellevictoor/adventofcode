fun run(executor: SolutionExecutor, inputFile: String): Number {
    return FileProcessor(inputFile, executor).count()
}

fun main() {
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
    println(run(Decoder(), "input-day8"))*/
    println(run(HeightMapSeeker(), "input-day9"))

}
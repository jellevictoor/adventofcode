fun run(executor: SolutionExecutor, inputFile: String): Int {
    return FileProcessor(inputFile, executor).count()
}

fun main() {
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

}
fun run(executor: SolutionExecutor, inputFile: String): Int {
    return FileProcessor(inputFile, executor).count()
}

fun main() {
    println("day 1")
    println(run(Counter(), "input-day1"))
    println(run(SlidingCounter(), "input-day1"))
    println("day 2")

}
fun run(counter:Countable) = FileCounter("input", counter).count()
fun main() {
    println(run(Counter()))
    println(run(SlidingCounter()))
}
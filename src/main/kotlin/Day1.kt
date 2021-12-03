class Counter : SolutionExecutor {
    override fun process(input: List<String>): Int {

        var count = 0
        val it = input.iterator()
        var previous = it.next().toInt()
        while (it.hasNext()) {
            val current = it.next().toInt()
            if (previous < current) {
                count++
            }
            previous = current
        }

        return count
    }
}
class SlidingCounter : SolutionExecutor {
    override fun process(input: List<String>): Int {
        var count = 0
        val zippedList = input
            .zip(input.subList(1, input.size))
            .zip(input.subList(2, input.size))
        val iterator = zippedList.iterator()
        var previous = sum(iterator.next())
        while (iterator.hasNext()) {
            val sum = sum(iterator.next())
            if (previous < sum) {
                count++
            }
            previous = sum
        }
        return count
    }

    private fun sum(value: Pair<Pair<String, String>, String>) =
        value.first.first.toInt() + value.first.second.toInt() + value.second.toInt()

}
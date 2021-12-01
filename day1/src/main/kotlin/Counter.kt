fun count(values: List<Int>): Int {

    var count = 0
    val it = values.iterator()
    var previous = it.next()
    while (it.hasNext()) {
        val current = it.next()
        if (previous < current) {
            count++
        }
        previous = current
    }

    return count
}

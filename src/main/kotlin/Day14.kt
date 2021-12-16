class Day14 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        val pairs = input
            .filter { it.contains("->") }
            .map { Pair(it.split(" -> ")[0], it.split(" -> ")[1]) }
        val staringTemplate = input.first()
        return Polymer(staringTemplate, pairs).apply(40).score()
    }
}

class Polymer(val template: String, val pairs: List<Pair<String, String>>) {
    val elementCount = pairs.map { it.second }.distinct().map { Pair(it, CharCounter()) }.toMap()

    fun apply(times: Int): Polymer {
        val map = pairs
            .map { Pair(it.first, it.second) }
            .toMap()
        for (window in template.windowed(2, 1, true)) {
            // add first char
            var replacement = window
            if (replacement.length > 1) {
                elementCount[replacement[0].toString()]?.add()
                for (i in 0 until times) {
                    replacement = "" + replacement[0] + map.get(replacement)
                    elementCount[replacement[1].toString()]?.add()
                }
            }
            //elementCount[replacement[1].toString()]?.add()
        }
        elementCount.get(template.last().toString())?.add()
        return this
    }

    private fun expand(window: String, times: Int, map: Map<String, String>): String {
        var result = window
        for (i in 0 until times) {
            result = result
                .windowed(2, 1)
                .map { map.get(it) ?: "" }
                .joinToString("")
        }
        return result
    }


    fun score(): Long {
        return elementCount.maxOf { it.value.count } - elementCount.minOf { it.value.count }
    }

    private fun s(template: String, pairs: List<Pair<String, String>>): String {
        val newtemplate: String = template
            .windowed(2, 1, true)
            .map { pair ->
                pairs.filter { it.first == pair }.map { it.second }.firstOrNull() ?: pair
            }.joinToString("")
        return newtemplate
    }
}

data class CharCounter(var count: Long = 0L) {
    fun add() = count++
}

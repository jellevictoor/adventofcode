class Day12 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        return Graph.of(input.map { Pair(it.split("-")[0], it.split("-")[1]) }).paths().size
    }

}

class Graph(val input: Map<CaveNode, List<CaveNode>>) {
    fun paths(): List<Path> {
        return input
            .filter { it.key.isStart() }
            .map { traverse(it.key, it.value, Path.emptyPath(it.key)) }
            .flatten()
    }

    private fun traverse(key: CaveNode, nextNodes: List<CaveNode>, path: Path): List<Path> {
        println(path)
        if (key.isEnd()) {
            return listOf(path)
        } else {
            return nextNodes
                .filter { path.canVisit(it) }
                .map { path.expandPath(it) } // listOf(A, b)
                .map { traverse(it.last(), getNextNodes(it.last()), it) }
                .flatten()
        }
    }

    fun getNextNodes(it: CaveNode): List<CaveNode> = input.getOrDefault(it, emptyList())

    companion object {
        fun of(input: List<Pair<String, String>>): Graph {
            val map = mutableMapOf<CaveNode, MutableList<CaveNode>>()
            input
                .forEach {
                    val from = CaveNode(it.first)
                    map.putIfAbsent(from, mutableListOf())
                    val to = CaveNode(it.second)
                    map[from]?.add(to)
                    map.putIfAbsent(to, mutableListOf())
                    map[to]?.add(from)
                }
            val immutable = map
                .map { Pair(it.key, it.value.toList()) }
                .toMap()
            return Graph(immutable)
        }
    }
}

data class Path(val nodes: List<CaveNode>) {

    fun expandPath(cave: CaveNode) = Path(nodes + cave)

    fun last() = nodes.last()
    override fun toString(): String {
        return nodes.joinToString(",")
    }

    fun contains(node: CaveNode) = nodes.contains(node)
    fun canVisit(it: CaveNode): Boolean {
        return !contains(it) || !it.isSmall()
    }

    companion object {
        fun emptyPath(cave: CaveNode): Path = Path(listOf(cave))
    }
}


data class CaveNode(val name: String) {
    fun isSmall() = name.all { Character.isLowerCase(it) }
    fun isStart() = name == "start"
    fun isEnd() = name == "end"
    override fun toString(): String {
        return name
    }
}
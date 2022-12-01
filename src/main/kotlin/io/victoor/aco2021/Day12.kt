package io.victoor.aco2021

import io.victoor.aoc.SolutionExecutor

class Day12 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        return Graph.of(input.map { Pair(it.split("-")[0], it.split("-")[1]) }).paths().size
    }

}

class Graph(val input: Map<CaveNode, List<CaveNode>>) {
    var smallCaveToVisitTwice: String? = null
    fun paths(): List<Path> {
        return input
            .filter { it.key.isStart() }
            .map { traverse(it.key, it.value, Path.emptyPath(it.key)) }
            .flatten()
    }

    private fun traverse(key: CaveNode, nextNodes: List<CaveNode>, path: Path): List<Path> {
        if (key.isEnd()) {
            return listOf(path)
        } else {
            return nextNodes
                .filter { path.canVisit(it) }
                .map {
                    smallCaveToVisitTwice = path.findTwiceVisitSmallCave()
                    it
                }
                .map { path.expandPath(it) }
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

    fun canVisit(node: CaveNode, smallCaveToVisitTwice: String? = null): Boolean {
        if (node.isStart()) {
            return false
        } else if (node.isSmall()) {
            if (contains(node) && smallCaveToVisitTwice == null) {
                return nodes
                    .filter { it.isSmall() }
                    .groupingBy { it }
                    .eachCount()
                    .filter { it.value > 1 }
                    .isEmpty()
            } else {
                return true
            }
        } else {
            return true
        }
    }

    fun findTwiceVisitSmallCave(): String? {
        return nodes
            .filter { it.isSmall() }
            .groupingBy { it }
            .eachCount()
            .filter { it.value > 1 }
            .map { it.key }
            .map { it.name }
            .firstOrNull()
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
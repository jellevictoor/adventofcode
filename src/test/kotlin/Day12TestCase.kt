import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

public class Day12TestCase {
    @Test
    fun smallCave() {
        assertFalse { CaveNode("A").isSmall() }
        assertTrue { CaveNode("a").isSmall() }
    }

    @Test
    fun isEnd() {
        assertTrue { CaveNode("end").isEnd() }
        assertFalse { CaveNode("start").isEnd() }
        assertFalse { CaveNode("A").isEnd() }
        assertFalse { CaveNode("a").isEnd() }
    }

    @Test
    fun isStart() {
        assertTrue { CaveNode("start").isStart() }
        assertFalse { CaveNode("end").isStart() }
        assertFalse { CaveNode("A").isStart() }
        assertFalse { CaveNode("a").isStart() }
    }

    @Test
    fun expandPath() {
        val expected = Path(listOf(CaveNode("start"), CaveNode("end")))
        assertEquals(expected, Path(listOf(CaveNode("start"))).expandPath(CaveNode("end")))
    }

    @Test
    fun getLast() {
        val last = CaveNode("end")
        val expected = Path(listOf(CaveNode("start"), CaveNode("end"))).last()
        assertEquals(expected, last)

    }

    @Test
    fun iterateSingleShortPath() {
        val input = mapOf(
            Pair(CaveNode("start"), listOf(CaveNode("A"))),
            Pair(CaveNode("A"), listOf(CaveNode("end"))),
        )
        val paths = Graph(input).paths()
        assertEquals(1, paths.size)
        assertEquals(
            listOf(
                Path(
                    listOf(
                        CaveNode("start"),
                        CaveNode("A"),
                        CaveNode("end")
                    )
                )
            ), paths
        )
    }

    @Test
    fun iterateDoubleShortPath() {
        val input = mapOf(
            Pair(CaveNode("start"), listOf(CaveNode("A"))),
            Pair(CaveNode("A"), listOf(CaveNode("B"), CaveNode("end"))),
            Pair(CaveNode("B"), listOf(CaveNode("end")))
        )
        val paths = Graph(input).paths()
        assertEquals(2, paths.size)
        Assert.assertTrue(
            listOf(
                Path(
                    listOf(
                        CaveNode("start"),
                        CaveNode("A"),
                        CaveNode("end")
                    )
                ),
                Path(
                    listOf(
                        CaveNode("start"),
                        CaveNode("A"),
                        CaveNode("B"),
                        CaveNode("end")
                    )
                )
            ).containsAll(paths)
        )
    }

    @Test
    fun nextNodeTest() {
        val input = mapOf(
            Pair(CaveNode("start"), listOf(CaveNode("A"))),
            Pair(CaveNode("A"), listOf(CaveNode("B"), CaveNode("end"))),
            Pair(CaveNode("B"), listOf(CaveNode("end")))
        )
        val paths = Graph(input).getNextNodes(CaveNode("A"))
        assertEquals(2, paths.size)
        assertEquals(listOf(CaveNode("B"), CaveNode("end")), paths)

    }

    @Test
    fun iterateSingleLongPath() {
        val input = mapOf(
            Pair(CaveNode("start"), listOf(CaveNode("A"))),
            Pair(CaveNode("A"), listOf(CaveNode("B"))),
            Pair(CaveNode("B"), listOf(CaveNode("end"))),
        )
        val paths = Graph(input).paths()
        assertEquals(1, paths.size)
        assertEquals(
            listOf(
                Path(
                    listOf(
                        CaveNode("start"),
                        CaveNode("A"),
                        CaveNode("B"),
                        CaveNode("end")
                    )
                )
            ), paths
        )
    }

    @Test
    fun testSmallGraph() {
        val input = listOf(
            Pair("start", "A"),
            Pair("start", "b"),
            Pair("A", "c"),
            Pair("b", "d"),
            Pair("A", "end"),
            Pair("b", "end")
        )
        val actual = Graph.of(input).paths()
        val expected = listOf(
            "start,A,b,A,c,A,end",
            "start,A,b,A,end",
            "start,A,b,end",
            "start,A,c,A,b,A,end",
            "start,A,c,A,b,end",
            "start,A,c,A,end",
            "start,A,end",
            "start,b,A,c,A,end",
            "start,b,A,end",
            "start,b,end"
        )
        assertEquals(expected, actual.map { it.toString() })
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
                .forEach { it ->
                    val from = CaveNode(it.first)
                    map.putIfAbsent(from, mutableListOf())
                    map[CaveNode(it.first)]?.add(CaveNode(it.second))
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



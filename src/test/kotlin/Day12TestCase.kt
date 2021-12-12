import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day12TestCase {
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
    fun canVisit() {
        var path = Path(listOf(CaveNode("start"), CaveNode("A"), CaveNode("b")))
        assertEquals(true, path.canVisit(CaveNode("A")))
        assertEquals(true, path.canVisit(CaveNode("b")))
        path = Path(listOf(CaveNode("start"), CaveNode("A"), CaveNode("b"), CaveNode("b")))
        assertEquals(false, path.canVisit(CaveNode("b")))
        assertEquals(true, path.canVisit(CaveNode("c")))
        path = Path(listOf(CaveNode("start"), CaveNode("A"), CaveNode("b"), CaveNode("b"), CaveNode("c")))
        assertEquals(false, path.canVisit(CaveNode("c")))
    }

    @Test
    fun testSmallGraph() {
        val input = listOf(
            Pair("start", "A"),
            Pair("start", "b"),
            Pair("A", "c"),
            Pair("A", "b"),
            Pair("b", "d"),
            Pair("A", "end"),
            Pair("b", "end")
        )
        val actual = Graph.of(input).paths()
        val expected = listOf(
            "start,A,b,A,b,A,c,A,end",
            "start,A,b,A,b,A,end",
            "start,A,b,A,b,end",
            "start,A,b,A,c,A,b,A,end",
            "start,A,b,A,c,A,b,end",
            "start,A,b,A,c,A,c,A,end",
            "start,A,b,A,c,A,end",
            "start,A,b,A,end",
            "start,A,b,d,b,A,c,A,end",
            "start,A,b,d,b,A,end",
            "start,A,b,d,b,end",
            "start,A,b,end",
            "start,A,c,A,b,A,b,A,end",
            "start,A,c,A,b,A,b,end",
            "start,A,c,A,b,A,c,A,end",
            "start,A,c,A,b,A,end",
            "start,A,c,A,b,d,b,A,end",
            "start,A,c,A,b,d,b,end",
            "start,A,c,A,b,end",
            "start,A,c,A,c,A,b,A,end",
            "start,A,c,A,c,A,b,end",
            "start,A,c,A,c,A,end",
            "start,A,c,A,end",
            "start,A,end",
            "start,b,A,b,A,c,A,end",
            "start,b,A,b,A,end",
            "start,b,A,b,end",
            "start,b,A,c,A,b,A,end",
            "start,b,A,c,A,b,end",
            "start,b,A,c,A,c,A,end",
            "start,b,A,c,A,end",
            "start,b,A,end",
            "start,b,d,b,A,c,A,end",
            "start,b,d,b,A,end",
            "start,b,d,b,end",
            "start,b,end"
        )
        actual.forEach { println(it) }
        assertEquals( 36,actual.size )
        assertTrue { expected.containsAll(actual.map { it.toString() }) }
    }

    @Test
    fun largerGraph() {
        val input = listOf(
            Pair("dc", "end"),
            Pair("HN", "start"),
            Pair("start", "kj"),
            Pair("dc", "start"),
            Pair("dc", "HN"),
            Pair("LN", "dc"),
            Pair("HN", "end"),
            Pair("kj", "sa"),
            Pair("kj", "HN"),
            Pair("kj", "dc")
        )
        val actual = Graph.of(input).paths()
        assertEquals( 103,actual.size )
    }

    @Test
    fun superLarge() {
        val input = listOf(
            Pair("fs", "end"),
            Pair("he", "DX"),
            Pair("fs", "he"),
            Pair("start", "DX"),
            Pair("pj", "DX"),
            Pair("end", "zg"),
            Pair("zg", "sl"),
            Pair("zg", "pj"),
            Pair("pj", "he"),
            Pair("RW", "he"),
            Pair("fs", "DX"),
            Pair("pj", "RW"),
            Pair("zg", "RW"),
            Pair("start", "pj"),
            Pair("he", "WI"),
            Pair("zg", "he"),
            Pair("pj", "fs"),
            Pair("start", "RW")
        )
        assertEquals(3509, Graph.of(input).paths().size)

    }
}




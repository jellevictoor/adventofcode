package io.victoor.aco2021
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class Day13TestCase {
    @Test
    fun exampleInput() {
        val input = listOf(
            Pair(6, 10),
            Pair(0, 14),
            Pair(9, 10),
            Pair(0, 3),
            Pair(10, 4),
            Pair(4, 11),
            Pair(6, 0),
            Pair(6, 12),
            Pair(4, 1),
            Pair(0, 13),
            Pair(10, 12),
            Pair(3, 4),
            Pair(3, 0),
            Pair(8, 4),
            Pair(1, 10),
            Pair(2, 14),
            Pair(8, 10),
            Pair(9, 0)
        )
        val from = Paper.from(input)
        val actual = from.foldY(7)
        assertEquals(actual.getDots(), 17)
    }

    @Test
    fun fold1() {
        val input = listOf(Pair(2, 0))
        val from = Paper.from(input)
        val foldY = from.foldX(1)
        Assert.assertEquals(1, foldY.state.count { it -> it == Dot(0, 0) })
    }
}
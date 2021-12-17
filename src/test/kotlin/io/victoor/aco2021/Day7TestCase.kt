package io.victoor.aco2021
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class Day7TestCase {
    @Test
    fun firstExample() {
        val input = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
        val expected = 168
        Assert.assertEquals(expected, CrabGroup(input).optimalFuelUsage())
    }

    @Test
    fun validateMoveCost() {
        assertEquals(6, CrabGroup(listOf()).moveCost(3))
        assertEquals(10, CrabGroup(listOf()).moveCost(4))
    }
}
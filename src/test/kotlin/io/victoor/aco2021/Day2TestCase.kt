package io.victoor.aco2021
import org.junit.Test
import kotlin.test.assertEquals

class Day2TestCase {
    @Test
    fun exampleTest() {
        val input = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
        )
        val expected = 900
        assertEquals(expected, nagivate(input))
    }
}
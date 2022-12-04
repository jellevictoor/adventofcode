package io.victoor.aco2022

import org.junit.Assert
import org.junit.Test

class Day4TestCase {
    @Test
    fun exampleInput() {
        val overlaps = Day4().process(
            ("2-4,6-8\n" +
                    "2-3,4-5\n" +
                    "5-7,7-9\n" +
                    "2-8,3-7\n" +
                    "6-6,4-6\n" +
                    "2-6,4-8").split("\n")
        )
        Assert.assertEquals(4,overlaps)
    }
}
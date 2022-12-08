package io.victoor.aco2022

import org.junit.Assert
import org.junit.Test

class Day8TestCase {
    @Test
    fun inputTestCase() {
        val input = ("30373\n" +
                "25512\n" +
                "65332\n" +
                "33549\n" +
                "35390").split("\n")
        val process = Day8().process(input)
        Assert.assertEquals(8, process)
    }
}
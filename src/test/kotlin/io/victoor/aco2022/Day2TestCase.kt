package io.victoor.aco2022

import org.junit.Assert
import org.junit.Test

class Day2TestCase {
    @Test
    fun exampleInput() {
        val input = ("A Y\n" +
                "B X\n" +
                "C Z\n").split('\n')
        val score = Day2().process(input.subList(0, 1))
        Assert.assertEquals(score, 4)
    }
    @Test
    fun exampleInput1() {
        val input = ("A Y\n" +
                "B X\n" +
                "C Z\n").split('\n')
        val score = Day2().process(input.subList(1, 2))
        Assert.assertEquals(1, score)
    }
    @Test
    fun exampleInput2() {
        val input = ("A Y\n" +
                "B X\n" +
                "C Z\n").split('\n')
        val score = Day2().process(input.subList(2, 3))
        Assert.assertEquals(7 ,score)
    }
}
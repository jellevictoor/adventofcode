package io.victoor.aco2022

import org.junit.Assert
import org.junit.Test

class Day1TestCase {
    @Test
    fun elfWithMostCalories() {
        val input = listOf(
            "1000",
            "2000",
            "3000",
            "",
            "4000",
            "",
            "5000",
            "6000",
            "",
            "7000",
            "8000",
            "9000",
            "",
            "10000"
        )
        val elfWithMostCalories = findElfWithMostCalories(input)

        Assert.assertEquals(24000, elfWithMostCalories.first().totalCalories())
    }

    @Test
    fun topThreeElvesWithMostCalories() {
        val input = listOf(
            "1000",
            "2000",
            "3000",
            "",
            "4000",
            "",
            "5000",
            "6000",
            "",
            "7000",
            "8000",
            "9000",
            "",
            "10000"
        )
        val elfWithMostCalories = Day1().totalCalories(input, 3)

        Assert.assertEquals(45000, elfWithMostCalories)
    }
}
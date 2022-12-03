package io.victoor.aco2022

import org.junit.Assert
import org.junit.Test

class Day3TestCase {
    @Test
    fun rucksacktest() {
        val input = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
                "PmmdzqPrVvPwwTWBwg\n" +
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
                "ttgJtRGJQctTZtZT\n" +
                "CrZsJsPPZsGzwwsLwLmpwMDw\n"
        val outcome = Day3().process(input.split("\n"))
        Assert.assertEquals(outcome, 157)
    }
}
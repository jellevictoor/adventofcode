package io.victoor.aco2022

import org.junit.Assert
import org.junit.Test

class Day6TestCase {
    @Test
    fun startMessage() {
        Assert.assertEquals(Day6().firstFirstOccurred(listOf("mjqjpqmgbljsphdztnvjfqwrcgsmlb"), 4), 7)
        Assert.assertEquals(Day6().firstFirstOccurred(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz"), 4), 5)
        Assert.assertEquals(Day6().firstFirstOccurred(listOf("nppdvjthqldpwncqszvftbrmjlhg"), 4), 6)
        Assert.assertEquals(Day6().firstFirstOccurred(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"), 4), 10)
        Assert.assertEquals(Day6().firstFirstOccurred(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"), 4), 11)
    }

    @Test
    fun messageStart() {
        Assert.assertEquals(Day6().firstFirstOccurred(listOf("mjqjpqmgbljsphdztnvjfqwrcgsmlb"), 14), 19)
        Assert.assertEquals(Day6().firstFirstOccurred(listOf("bvwbjplbgvbhsrlpgdmjqwftvncz"), 14), 23)
        Assert.assertEquals(Day6().firstFirstOccurred(listOf("nppdvjthqldpwncqszvftbrmjlhg"), 14), 23)
        Assert.assertEquals(Day6().firstFirstOccurred(listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"), 14), 29)
        Assert.assertEquals(Day6().firstFirstOccurred(listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"), 14), 26)
    }
}
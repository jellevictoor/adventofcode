package io.victoor.aco2021

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class Day21TestCase {
    @Test
    fun playGame() {
        val player1 = Player("player1", 0, 4)
        val player2 = Player("player2", 0, 8)

        val finalScore = Day21().playGame(listOf(player1, player2))
        assertEquals(444356092776315L, finalScore)
    }
}
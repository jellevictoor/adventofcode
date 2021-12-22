package io.victoor.aco2021

import io.victoor.aco2021.model.game.DeterministicDice
import io.victoor.aco2021.model.game.Game
import io.victoor.aco2021.model.game.Player
import io.victoor.aco2021.model.game.GamePosition
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class Day21TestCase {
    @Test
    fun playerExample() {
        val player = Player("player1", 0)
        val game = Game(10, listOf(Pair(player, GamePosition(7))))
        game.move(player, 2 + 2 + 1)
        assertEquals(2, player.score)
        assertNull(game.getWinner())
    }

    @Test
    fun testDie() {
        val die = DeterministicDice(100)
        assertEquals(1, die.roll())
        assertEquals(2, die.roll())
        assertEquals(3, die.roll())
    }

    @Test
    fun twoPlayerExample() {
        val player1 = Player("player1", 0)
        val player2 = Player("player2", 0)
        val game = Game(
            10, listOf(
                Pair(player1, GamePosition(4)),
                Pair(player2, GamePosition(8))
            )
        )

        game.move(player1, 1 + 2 + 3)
        game.move(player2, 4 + 5 + 6)
        game.move(player1, 7 + 8 + 9)
        game.move(player2, 10 + 11 + 12)
        game.move(player1, 13 + 14 + 15)
        game.move(player2, 16 + 17 + 18)
        game.move(player1, 19 + 20 + 21)
        game.move(player2, 22 + 23 + 24)
        assertEquals(26, player1.score)
        assertEquals(22, player2.score)
        assertNull(game.getWinner())
    }

    @Test
    fun playGame() {
        val player1 = Player("player1", 0)
        val player2 = Player("player2", 0)

        val players = listOf(
            Pair(player1, GamePosition(4)),
            Pair(player2, GamePosition(8))
        )
        val finalScore = Day21().playGame(players, DeterministicDice(100))
        assertEquals(739785, finalScore)
    }
}
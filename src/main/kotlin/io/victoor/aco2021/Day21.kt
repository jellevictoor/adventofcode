package io.victoor.aco2021

import io.victoor.aco2021.model.game.DeterministicDice
import io.victoor.aco2021.model.game.Game
import io.victoor.aco2021.model.game.GamePosition
import io.victoor.aco2021.model.game.Player

class Day21 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        // not parsing today
        val player1 = Player("player1", 0)
        val player2 = Player("player2", 0)
        val players = listOf(
            Pair(player1, GamePosition(3)),
            Pair(player2, GamePosition(7))
        )
        return playGame(players, DeterministicDice(100))
    }

    fun playGame(
        players: List<Pair<Player, GamePosition>>,
        deterministicDice: DeterministicDice
    ): Number {
        val game = Game(
            10, players
        )
        var turn = 0
        while (game.getWinner() == null) {
            game.move(players[turn % 2].first, deterministicDice.roll() + deterministicDice.roll() + deterministicDice.roll())
            turn++
        }
        return game.score(deterministicDice)
    }

}
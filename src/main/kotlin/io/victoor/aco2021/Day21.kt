package io.victoor.aco2021


data class Player(val name: String, val score: Int, val position: Int) {
    fun roll(dieRollValue: Int): Player {
        // position is 0 based, score is 1 based
        val newPosition = (position - 1 + dieRollValue) % 10 + 1
        val newScore = score + newPosition
        return Player(name, newScore, newPosition)
    }
}

class Day21 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        // not parsing today
        val player1 = Player("player1", 0, 3)
        val player2 = Player("player2", 0, 7)
        return playGame(listOf(player1, player2))
    }

    fun playGame(
        players: List<Player>
    ): Number {
        val universes = playTurn(players[0], players[1], mutableMapOf())
        return universes.toList().maxOf { it }
    }

    /** dynamic programming + memozation*/
    private fun playTurn(
        playerWhoCanPlay: Player,
        otherPlayer: Player,
        cache: MutableMap<Pair<Player, Player>, Pair<Long, Long>>
    ): Pair<Long, Long> {
        var answer = Pair(0L, 0L)
        if (playerWhoCanPlay.score >= 21) {
            return Pair(1, 0)
        } else if (otherPlayer.score >= 21) {
            return Pair(0, 1)
        }
        val gameState = Pair(playerWhoCanPlay, otherPlayer)
        if (cache.containsKey(gameState)) {
            return cache[gameState]!!
        }
        (1..3).forEach { die1 ->
            (1..3).forEach { die2 ->
                (1..3).forEach { die3 ->
                    val newPlayer = playerWhoCanPlay.roll(die1 + die2 + die3)
                    val winsPerUniverse = playTurn(otherPlayer, newPlayer, cache)
                    // reverse here because of switched players above
                    answer = Pair(answer.first + winsPerUniverse.second, answer.second + winsPerUniverse.first)
                }
            }
        }
        cache[Pair(playerWhoCanPlay, otherPlayer)] = answer
        return answer
    }

}
package io.victoor.aco2021.model.game

class Game(val size: Int, players: List<Pair<Player, GamePosition>>) {
    val state = players.toMap()
    fun move(player: Player, roll: Int): Int {
        var nextPosition = (roll + state.get(player)!!.position) % size
        if (nextPosition == 0) {
            nextPosition = size
        }
        state.get(player)?.position = nextPosition
        player.score += nextPosition
        return nextPosition
    }

    fun getWinner(): Player? = state.filter { it.key.score >= 1000 }.map { it.key }.firstOrNull()
    fun score(die: DeterministicDice): Number {
        if (getWinner() != null) {
            return state.filter { it.key != getWinner() }
                .map { it.key.score * die.rolls }
                .sum()
        } else {
            return 0
        }
    }
}




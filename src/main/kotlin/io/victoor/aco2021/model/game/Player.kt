package io.victoor.aco2021.model.game

data class Player(val name: String, val score: Int, val position: Int) {
    fun roll(dieRollValue: Int): Player {
        // position is 0 based, score is 1 based
        val newPosition = (position - 1 + dieRollValue) % 10 + 1
        val newScore = score + newPosition
        return Player(name, newScore, newPosition)
    }
}

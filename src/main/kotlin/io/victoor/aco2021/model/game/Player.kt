package io.victoor.aco2021.model.game

data class Player(val name: String, val initialScore: Int) {
    var score: Int = initialScore
}

package io.victoor.aco2021.model.game

class DeterministicDice(val sides: Int) {
    var value = 0;
    var rolls = 0
    fun roll(): Int {
        value = ++value % sides
        if (value == 0) {
            value = sides
        }
        rolls++
        return value
    }

}

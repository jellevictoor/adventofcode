package io.victoor.aco2021

import java.util.*

class Navigator : SolutionExecutor {
    override fun process(input: List<String>): Int {
        return nagivate(input)
    }
}

fun nagivate(input: List<String>): Int {
    var position = Position.startPosition()
    input.forEach { position = move(position, it) }
    return position.x * position.y
}

enum class Directions(private val value: String) {
    FORWARD("forward"),
    DOWN("down"),
    UP("up");
}

fun move(position: Position, stringValue: String): Position {
    val split = stringValue.split(" ");
    val action = Directions.valueOf(split.get(0).uppercase(Locale.getDefault()))
    val value = split.get(1)
    when (action) {
        Directions.FORWARD -> return position.moveHorizontal(value.toInt())
        Directions.DOWN -> return position.moveDepth(value.toInt())
        Directions.UP -> return position.moveDepth(value.toInt() * -1)
    }
}

data class Position(val x: Int, val y: Int, val aim: Int) {
    companion object {
        fun startPosition() = Position(0, 0, 0)
    }

    fun getPosition() = x * y
    fun moveDepth(value: Int): Position {
        return Position(this.x, y, aim + value)
    }

    fun moveHorizontal(value: Int): Position {
        return Position(x + (value * aim), y + value, aim)
    }
}
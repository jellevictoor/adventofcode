package io.victoor.aco2022

import io.victoor.aoc.SolutionExecutor

class Day2 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        return input.map { RockGame.fromString(it) }.map { it.getScore() }.sum()
    }


    data class RockGame(val a: Symbol, val b: Symbol) {
        companion object {
            fun fromString(it: String): RockGame {
                val game = it.split(" ")
                return RockGame(
                    Symbol.fromChar(game.get(0).get(0)),
                    Symbol.fromChar(game.get(1).get(0))

                )
            }
        }

        fun hasWon(): Boolean {
            return a == Symbol.ROCK && b == Symbol.PAPER ||
                    a == Symbol.PAPER && b == Symbol.SCISSOR ||
                    a == Symbol.SCISSOR && b == Symbol.ROCK
        }

        fun isDraw(): Boolean {
            return a == b
        }

        fun getScore(): Int {
            return (if (hasWon()) 6 else 0) + b.score + (if (isDraw()) 3 else 0)
        }
    }

    enum class Symbol(val score: Int) {

        ROCK(1), PAPER(2), SCISSOR(3);

        companion object {
            fun fromChar(c: Char): Symbol {
                return when (c) {
                    'A' -> ROCK
                    'B' -> PAPER
                    'C' -> SCISSOR
                    'X' -> ROCK
                    'Y' -> PAPER
                    'Z' -> SCISSOR
                    else -> throw UnsupportedOperationException()
                }
            }
        }
    }
}
package io.victoor.aco2022

import io.victoor.aoc.SolutionExecutor

class Day2 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        return input.map { RockGame.fromString(it) }
            .map {
it
            }.map { it.getScore() }.sum()
    }


    data class RockGame(val a: Symbol, val b: Outcome) {
        companion object {
            fun fromString(it: String): RockGame {
                val game = it.split(" ")
                return RockGame(
                    Symbol.fromChar(game.get(0).get(0)),
                    Outcome.fromChar(game.get(1).get(0))
                )
            }
        }


        fun getScore(): Int {
            return when (b){
                Outcome.WIN -> return 6 + a.getWinningCounterPlay().score
                Outcome.LOSS -> return 0 + a.getLosingCounterPlay().score
                Outcome.DRAW -> return 3 + a.score
            }
        }
    }

    enum class Outcome() {

        WIN, LOSS, DRAW;

        companion object {
            fun fromChar(c: Char): Outcome {
                return when (c) {
                    'X' -> LOSS
                    'Y' -> DRAW
                    'Z' -> WIN
                    else -> throw UnsupportedOperationException()
                }
            }
        }
    }

    enum class Symbol(val score: Int) {

        ROCK(1), PAPER(2), SCISSOR(3);

        fun getWinningCounterPlay():Symbol{
           return when(this){
                ROCK -> PAPER
                PAPER -> SCISSOR
                SCISSOR -> ROCK
            }
        }
        fun getLosingCounterPlay():Symbol{
            return when(this){
                ROCK -> SCISSOR
                PAPER -> ROCK
                SCISSOR -> PAPER
            }
        }

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
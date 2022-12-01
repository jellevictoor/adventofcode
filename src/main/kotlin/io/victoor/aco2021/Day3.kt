package io.victoor.aco2021

import io.victoor.aoc.SolutionExecutor
import java.lang.Math.abs

class PowerConsumptionCalculator() : SolutionExecutor {

    override fun process(input: List<String>): Int {
        val powerConsumption: PowerConsumption = powerConsumption(input)
        return powerConsumption.epsilon * powerConsumption.gamma
    }

    fun powerConsumption(metrics: List<String>): PowerConsumption {
        val powerConsumption: PowerConsumption
        val transposedMetrics = transposeToInt(metrics)
        val frequentNumber = transposedMetrics
            .map { getMostFrequentBit(it) }
            .fold("") { prev, actual -> prev + actual.toString() }
        val leastNumber = invert(frequentNumber)
        powerConsumption =
            PowerConsumption(frequentNumber.toInt(2), leastNumber.toInt(2))
        return powerConsumption
    }
}

class LifeSupportCalculator() : SolutionExecutor {

    override fun process(input: List<String>): Int {
        val lifeSupportRating: LifeSupportRating = lifeSupport(input)
        return lifeSupportRating.co2Scrubber * lifeSupportRating.oxygenRating
    }

    fun lifeSupport(metrics: List<String>): LifeSupportRating {
        return LifeSupportRating(
            reduceLifeSupportMetrics(metrics,
                { one, zero -> one > zero })[0].toInt(2),
            reduceLifeSupportMetrics(metrics,
                { one, zero -> one <= zero })[0].toInt(2),

            )
    }

}

fun transposeToInt(metrics: List<String>): List<List<Int>> {
    val transposedMetrics = transpose(metrics.map {
        it.toCharArray().toList().map { Character.getNumericValue(it) }
    })
    return transposedMetrics
}


private fun reduceLifeSupportMetrics(
    metrics: List<String>, comparator: (zero: Int, one: Int) -> Boolean,
    index: Int = 0
): List<String> {
    var result: List<String>;
    if (metrics.size > 1) {
        val (zeroList, oneList) = metrics.partition { it[index] == '0' }
        if (comparator(zeroList.size, oneList.size)) {
            result = reduceLifeSupportMetrics(zeroList, comparator, index + 1)
        } else {
            result = reduceLifeSupportMetrics(oneList, comparator, index + 1)
        }
    } else {
        result = metrics
    }
    return result
}


fun invert(value: String): String {
    return value
        .map { abs(Character.getNumericValue(it) - 1) }
        .fold("") { prev, actual -> prev + actual.toString() }
}

fun getMostFrequentBit(it: List<Int>): Int {
    if (it.sum() > it.size / 2) {
        return 1;
    } else {
        return 0;
    }
}

fun transpose(metrics: List<List<Int>>): List<List<Int>> {
    val transpose = Array(metrics.get(0).size) { IntArray(metrics.size) }

    for (i in metrics.indices) {
        for (j in 0 until metrics[0].size) {
            transpose[j][i] = metrics[i][j]
        }
    }
    return transpose.map { it.toList() }.toList()
}

data class PowerConsumption(val gamma: Int, val epsilon: Int)
data class LifeSupportRating(val oxygenRating: Int, val co2Scrubber: Int)
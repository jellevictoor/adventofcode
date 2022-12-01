package io.victoor.aco2021

import io.victoor.aco2021.model.Point
import io.victoor.aoc.SolutionExecutor

class Day17 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        val split = input.first().replace("target area: ", "")
            .split(", ")
        val xRange = split[0].substring(2).split("..")
        val yRange = split[1].substring(2).split("..")
        val target = Target(Point(xRange[0].toInt(), yRange[0].toInt()), Point(xRange[1].toInt(), yRange[1].toInt()))
        val bestLauncher = ProbeSimulator().findBestProbeSettings(target)
        return bestLauncher.map { Pair(it.probe.yVelocity, it.probe.xVelocity) }.distinct().count()
    }

}

class ProbeSimulator() {

    fun findBestProbeSettings(target: Target): List<ProbeLauncher> {
        val maxXVelocity = target.max.x // cant shoot further of the max target
        val maxYVelocity = -(target.min.y + 1)// part above y and part below y -> drop can max be
        val results = mutableListOf<ProbeLauncher>()
        for (x in 0..maxXVelocity) {
            for (y in target.min.y..maxYVelocity) {
               val launcher = ProbeLauncher(Probe.initalProbe(x, y), target)
                launcher.simulate()
                if (launcher.isInTarget()) {
                    results.add(launcher)
                }
            }
        }
        return results.toList()

    }
}

class ProbeLauncher(val probe: Probe, val target: Target) {
    val trajectory = mutableListOf(probe)
    fun step(): Probe {
        val probe = trajectory.last().nextPosition()
        trajectory.add(probe)
        return probe
    }

    fun simulate(): Probe {
        while (!isInTarget() && trajectory.last().position.x <= target.max.x && trajectory.last().position.y >= target.min.y) {
            step()
        }
        val finalPosition = trajectory.last()
        return finalPosition
    }

    fun isInTarget(): Boolean {
        return target.contains(trajectory.last().position)
    }

    override fun toString(): String {

        val minX = minOf(trajectory.minOf { it.position.x }, target.min.x)
        val maxX = maxOf(trajectory.maxOf { it.position.x }, target.max.x)
        val minY = minOf(trajectory.minOf { it.position.y }, target.min.y)
        val maxY = maxOf(trajectory.maxOf { it.position.y }, target.max.y)
        val grid = Array(maxY - minY + 1) { CharArray(maxX - minX + 1) { '.' } }
        val shiftX = if (minX < 0) -1 * minX else minX
        val shiftY = if (minY < 0) -1 * minY else minY
        for (x in target.min.x..target.max.x) {
            for (y in target.min.y..target.max.y) {
                grid[y + shiftY][x + shiftX] = 'T'
            }
        }
        trajectory.forEach { grid[it.position.y + shiftY][it.position.x + shiftX] = '#' }
        val startPosition = trajectory.first().position
        grid[startPosition.y + shiftY][startPosition.x + shiftX] = 'S'
        /*grid
            .map { it.toList().joinToString("") }
            .joinToString("\n") +
                "\n"+*/
        return trajectory.first().toString() + " -> " + trajectory.last().toString()
    }
}

data class Probe(val position: Point, val xVelocity: Int, val yVelocity: Int) {

    fun nextPosition(): Probe {
        val newXVelocity = if (xVelocity > 0) {
            xVelocity - 1
        } else if (xVelocity < 0) {
            xVelocity + 1
        } else {
            xVelocity
        }
        return Probe(Point(position.x + xVelocity, position.y + yVelocity), newXVelocity, yVelocity - 1)
    }

    companion object {
        fun initalProbe(xVelocity: Int, yVelocity: Int) = Probe(Point(0, 0), xVelocity, yVelocity)
    }
}

data class Target(val min: Point, val max: Point) {
    fun contains(point: Point) = (min.x..max.x).contains(point.x) && (min.y..max.y).contains(point.y)
}

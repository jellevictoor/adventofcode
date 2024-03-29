package io.victoor.aco2021

import io.victoor.aco2021.model.Grid
import io.victoor.aco2021.model.Point
import io.victoor.aoc.SolutionExecutor
import java.util.*

class Day15 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        val grid = Grid.from(input).expand(5)
        val start = grid.lookup(Point(0, 0, 1))
        val end = grid.lookup(Point(grid.maxX, grid.maxY, 1))
        return Dijkstra(grid).shortestPath(start, end)
    }

}

class Dijkstra(val grid: Grid) {
    fun shortestPath(start: Point, end: Point): Int {
        val distances = grid.nodes.map { Pair(it, Distance(it)) }.toMap()
        distances.getValue(start).distance = 0
        val distanceQueue = PriorityQueue<Distance> { o1, o2 -> o1.distance.compareTo(o2.distance) }
        distanceQueue.add(Distance(start, distance = 0))
        while (distanceQueue.isNotEmpty()) {
            val distance = distanceQueue.poll()
            if (!distance.visited) {
                val current = distance
                current.visited = true
                for (neighbour in grid.adjecentLocations(current.point)) {
                    relax(current, distances.getValue(neighbour), neighbour.value, distanceQueue)
                }
            }
        }
        return distances.filter { it.key.x == end.x && it.key.y == end.y }.map { it.value }.first().distance
    }

    fun relax(from: Distance, to: Distance, length: Int, distanceQueue: PriorityQueue<Distance>) {
        if (from.distance + length < to.distance) {
            to.distance = from.distance + length
            distanceQueue.add(to)
        }
    }

    data class Distance(val point: Point, var distance: Int = Int.MAX_VALUE, var visited: Boolean = false)
}





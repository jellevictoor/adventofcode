package io.victoor.aco2021

import io.victoor.aco2021.model.Grid
import io.victoor.aco2021.model.Point

class HeightMapSeeker() : SolutionExecutor {
    override fun process(input: List<String>): Number {
        return HeightMap.from(input).getBasins()
            .sortedByDescending { it.size }
            .take(3)
            .map { it.size }
            .reduce { acc, i -> acc * i }
    }
}

class HeightMap(val grid: Grid) {
    fun getLowPoints(): List<Int> {
        return grid.getLowPoints().map { it.value }
    }

    fun getBasins(): List<Basin> {
        return Basin.from(grid)
    }

    fun getRiskLevel(): Int {
        return getLowPoints()
            .map { it + 1 }
            .sum()
    }

    companion object {
        fun from(input: List<String>): HeightMap {

            return HeightMap(Grid.from(input))
        }
    }

    data class Basin(val points: MutableList<Point>) {
        val size = points.size

        companion object {
            fun from(grid: Grid): List<Basin> {
                return grid.getLowPoints()
                    .map { point ->
                        val basin = Basin(seek(grid, point, Basin(mutableListOf(point))))
                        basin
                    }
            }

            private fun seek(grid: Grid, point: Point, basin: Basin): MutableList<Point> {
                val lowerPoints = grid.adjecentLocations(point)
                    .filter { !basin.points.contains(it) }
                    .filter { it.value < 9 }
                return if (lowerPoints.isEmpty())
                    basin.points
                else {
                    basin.points.addAll(lowerPoints)
                    lowerPoints
                        .map { seek(grid, it, basin) }
                        .flatten()
                        .toSet()
                        .toMutableList()
                }
            }
        }
    }
}


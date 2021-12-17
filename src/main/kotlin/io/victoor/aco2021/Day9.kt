package io.victoor.aco2021

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
        return grid.getBasins()
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

    data class Point(val x: Int, val y: Int, val value: Int = -1)

    data class Grid(val nodes: List<Point>) {
        val maxX = nodes.maxOf { it.x }
        private val minX = nodes.minOf { it.x }
        val maxY = nodes.maxOf { it.y }
        private val minY = nodes.minOf { it.y }
        private val nodeLookup = Array(maxY + 1) { Array<Point>(maxX + 1) { Point(-1, -1) } }

        init {
            nodes.forEach { nodeLookup[it.x][it.y] = it }
        }

        fun lookup(x: Int, y: Int): Point {
            return nodeLookup[x][y]
        }

        fun adjecentLocations(point: Point): List<Point> {
            return listOf(
                Point(point.x - 1, point.y),
                Point(point.x + 1, point.y),
                Point(point.x, point.y - 1),
                Point(point.x, point.y + 1)
            ).filter { it.x >= minX && it.y >= minY && it.x <= maxX && it.y <= maxY }
                .map { lookup(it.x, it.y) }
        }

        fun getLowPoints(): List<Point> {
            val lowPoints = mutableListOf<Point>()
            for (point in nodes) {
                val isLowPoint =
                    adjecentLocations(point)
                        .map { lookup(it.x, it.y) }
                        .all { it.value > point.value }
                if (isLowPoint) {
                    lowPoints.add(point)
                }
            }
            return lowPoints
        }

        fun getBasins(): List<Basin> {
            return Basin.from(this)
        }

        fun lookup(point: Point) = lookup(point.x, point.y)
        fun expand(times: Int): Grid {
            val expand = mutableListOf<Point>()
            for (i in 0 until times) {
                for (j in 0 until times) {
                    expand.addAll(nodes
                        .map {
                            val newPoint = Point(it.x + ((maxX + 1) * i), it.y + ((maxY + 1) * j), it.value + i + j)
                            if (newPoint.value > 9) {
                                Point(newPoint.x, newPoint.y, newPoint.value - 9)
                            } else {
                                newPoint
                            }
                        })
                }
            }
            return Grid(expand)
        }

        companion object {
            fun from(input: List<String>): Grid {
                val grid = mutableListOf<Point>()
                val list = input.map { it -> it.toCharArray().map { Character.getNumericValue(it) } }
                for (i in list.indices) {
                    for (j in 0 until list[i].size) {
                        grid.add(Point(i, j, list[i][j]))
                    }
                }
                return Grid(grid)
            }

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


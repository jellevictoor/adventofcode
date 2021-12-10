class HeightMapSeeker() : SolutionExecutor {
    override fun process(input: List<String>): Number {
        return HeightMap.from(input).getBasins()
            .sortedByDescending { it.size }
            .take(3)
            .map { it.size }
            .reduce { acc, i ->  acc * i }
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
            val grid = mutableListOf<Point>()
            val list = input.map { it -> it.toCharArray().map { Character.getNumericValue(it) } }
            for (i in list.indices) {
                for (j in 0 until list[i].size) {
                    grid.add(Point(i, j, list[i][j]))
                }
            }
            return HeightMap(Grid(grid))
        }
    }

    data class Point(val x: Int, val y: Int, val value: Int = -1)

    data class Grid(private val points: List<Point>) {
        private val maxX = points.maxOf { it.x }
        private val minX = points.minOf { it.x }
        private val maxY = points.maxOf { it.y }
        private val minY = points.minOf { it.y }
        fun lookup(x: Int, y: Int): Point {
            return points.first { it.x == x && it.y == y }
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
            for (point in points) {
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


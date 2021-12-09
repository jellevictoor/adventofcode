class HeightMapSeeker():SolutionExecutor {
    override fun process(input: List<String>): Number {
        return HeightMap.from(input).getRiskLevel()
    }
}

class HeightMap(val grid: Grid) {
    fun getLowPoints(): List<Int> {
        val lowPoints = mutableListOf<Point>()
        for (point in grid.points) {
            val isLowPoint = point
                .adjecentLocations()
                .filter { it.x >= grid.minX && it.y >= grid.minY && it.x <= grid.maxX && it.y <= grid.maxY }
                .map { grid.lookup(it.x,it.y) }
                .all { it.value > point.value }
            if (isLowPoint) {
                lowPoints.add(point)
            }

        }
        return lowPoints.map { it.value }
    }
    fun getRiskLevel():Int{
        return getLowPoints()
            .map { it +1 }
            .sum()
    }

    companion object {
        fun from(input: List<String>): HeightMap {
            val grid = mutableListOf<Point>()
            val list = input.map { it -> it.toCharArray().map { Character.getNumericValue(it) } }
            for (i in 0 until list.size) {
                for (j in 0 until list[i].size) {
                    grid.add(Point(i, j, list[i][j]))
                }
            }
            return HeightMap(Grid(grid))
        }
    }

    data class Point(val x: Int, val y: Int, val value: Int = -1) {
        fun adjecentLocations(): List<Point> {
            return listOf(Point(x - 1, y), Point(x + 1, y), Point(x, y - 1), Point(x, y + 1))
        }
    }

    data class Grid(val points: List<Point>) {
        val maxX = points.maxOf { it.x }
        val minX = points.minOf { it.x }
        val maxY = points.maxOf { it.y }
        val minY = points.minOf { it.y }
        fun lookup(x: Int, y: Int): Point {
            return points.first { it.x == x && it.y == y }
        }
    }
}


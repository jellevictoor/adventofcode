package io.victoor.aco2021.model

data class Grid(val nodes: List<Point>) {
    val maxX = nodes.maxOf { it.x }
    private val minX = nodes.minOf { it.x }
    val maxY = nodes.maxOf { it.y }
    private val minY = nodes.minOf { it.y }
    val nodeLookup = Array(maxY + 1) { Array<Point>(maxX + 1) { Point(-1, -1) } }

    init {
        nodes.forEach { nodeLookup[it.y][it.x] = it }
    }

    fun lookup(x: Int, y: Int): Point {
        return if (x >= minX && y >= minY && x <= maxX && y <= maxY) {
            nodeLookup[y][x]
        } else {
            Point(x, y, 0)
        }
    }

    fun infiniteAdjecentLocations(point: Point): List<Point> {
        val positions = mutableListOf<Point>()

        (-1 .. 1).forEach { yOffset ->
            (-1 .. 1).forEach { xOffset ->
                positions.add(Point(point.x+xOffset, point.y+yOffset))
            }
        }

        return positions.map { lookup(it.x, it.y) }
    }

    fun adjecentLocations(point: Point): List<Point> {
        return listOf(
            Point(point.x - 1, point.y),
            Point(point.x + 1, point.y),
            Point(point.x, point.y - 1),
            Point(point.x, point.y + 1)
        ).filter { contains(it.x, it.y) }
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

    fun contains(x: Int, y: Int) = x >= minX && y >= minY && x <= maxX && y <= maxY

    fun grow(i: Int): Grid {
        val expanded = Array(maxY + 1 + 2 * i) { y -> Array<Point>(maxX + 1 + 2 * i) { x -> Point(x, y, 0) } }
        nodes.forEach { expanded[it.x + i][it.y + i] = Point(it.x + i, it.y + i, it.value) }
        return Grid(expanded
            .map { it.toList() }
            .flatten())
    }

    companion object {
        fun from(input: List<String>): Grid {
            val grid = mutableListOf<Point>()
            val list = input.map { it -> it.toCharArray().map { Character.getNumericValue(it) } }
            for (y in list.indices) {
                for (x in 0 until list[y].size) {
                    grid.add(Point(x, y, list[y][x]))
                }
            }
            return Grid(grid)
        }

        fun fromLights(input: List<String>): Grid {
            val convertedInput = input
                .map { it.replace("#", "1") }
                .map { it.replace(".", "0") }
            return from(convertedInput)
        }

    }
}
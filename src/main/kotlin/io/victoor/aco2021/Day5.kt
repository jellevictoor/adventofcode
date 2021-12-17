package io.victoor.aco2021

class MapSolver : SolutionExecutor {
    override fun process(input: List<String>): Int {
        return SeaMap.from(input).getNoOfDangerousAreas(2)
    }
}

class SeaMap(trajectories: List<Trajectory>) {
    private val grid: Map<Point, Int>

    init {
        grid = trajectories
            .map { it.getPoints() } // trajects to points
            .flatten() // all points in one list
            .groupingBy { it } // group points by occurrence
            .eachCount() // count the number of same points
    }

    fun getNoOfDangerousAreas(dangerTreshold: Int): Int {
        return grid.filter { it.value >= dangerTreshold }.count()
    }

    companion object {
        fun from(trajectories: List<String>): SeaMap {
            val parsedTrajectories =
                trajectories
                    .map { Trajectory.from(it) }
            //.filter { it.isStraight() }
            return SeaMap(parsedTrajectories)
        }
    }
}

class Trajectory(val from: Point, val to: Point) {
    private fun isStraight(): Boolean = (from.x == to.x || from.y == to.y)
    fun getPoints(): List<Point> {
        val xs = getRangeOfPoints(from.x, to.x)
        val ys = getRangeOfPoints(from.y, to.y)

        if (isStraight()) {
            return sequence {
                for (x in xs) {
                    for (y in ys) {
                        yield(Point(x, y))
                    }
                }
            }.toList()
        } else {
            return xs.zip(ys).map { Point(it.first, it.second) }
        }
    }

    private fun getRangeOfPoints(from: Int, to: Int) = if (from > to) {
        (to..from).reversed()
    } else {
        (from..to)
    }.toList()

    companion object {
        fun from(line: String): Trajectory {
            val fromTo = line.split(" -> ")
            val parsedFromTo = fromTo
                .map { it.split(",") }
                .map { Point(it[0].toInt(), it[1].toInt()) }
            return Trajectory(parsedFromTo[0], parsedFromTo[1])
        }
    }
}

data class Point(val x: Int, val y: Int)

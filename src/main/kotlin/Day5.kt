class MapSolver : SolutionExecutor {
    override fun process(input: List<String>): Int {
        return SeaMap.from(input).getNoOfDangerousAreas(2)
    }
}

class SeaMap(
    trajectories: List<Trajectory>,
    private val gridSize: Int
) {
    private val grid: Map<Point, Int>

    init {
        grid = trajectories
            .map { it.getPoints() }
            .flatten()
            .groupingBy { it }
            .eachCount()

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
            val gridSize = parsedTrajectories
                .map { listOf(it.from, it.to) }
                .flatten()
                .map { listOf(it.x, it.y) }
                .flatten()
                .maxOf { it }
            return SeaMap(parsedTrajectories, gridSize + 1)
        }
    }
}

class Trajectory (val from: Point, val to: Point) {
    fun isStraight(): Boolean = (from.x == to.x || from.y == to.y)
    fun getPoints(): List<Point> {
        val xs = if (from.x > to.x) {
            (to.x..from.x).reversed()
        } else {
            (from.x..to.x)
        }.toList()

        val ys = if (from.y > to.y) {
            (to.y..from.y).reversed()
        } else {
            (from.y..to.y)
        }.toList()

        if (isStraight()) {
            val points = mutableListOf<Point>()
            for (x in xs) {
                for (y in ys) {
                    points.add(Point(x, y))
                }
            }
            return points
        } else {
            return xs.zip(ys).map { Point(it.first, it.second) }
        }
    }

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

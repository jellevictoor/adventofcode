import org.junit.Test
import kotlin.test.assertEquals

public class Day11TestCase {
    @Test
    fun octopusFlashes() {
        val cave = Cave.fromStartSituation(listOf(listOf(9)))
        val nextDayCave = cave.moveDay()
        assertEquals(1, nextDayCave.flashes)
    }

    @Test
    fun octopusNotFlashes() {
        val cave = Cave.fromStartSituation(listOf(listOf(2)))
        val nextDayCave = cave.moveDay()
        assertEquals(0, nextDayCave.flashes)
    }

    @Test
    fun octopusFlashes2Times() {
        val cave = Cave.fromStartSituation(listOf(listOf(9)))
        val nextDayCave = cave.moveDay(11)
        assertEquals(2, nextDayCave.flashes)
    }

    @Test
    fun fourOctopusses() {
        val cave = Cave.fromStartSituation(
            listOf(
                listOf(9, 1),
                listOf(1, 1)
            )
        )
        val nextDayCave = cave.moveDay(1)
        assertEquals(1, nextDayCave.flashes)
        assertEquals(3, nextDayCave.octopusses.count { it.charge == 3 })
    }

    @Test
    fun findAdjecentOctopusses() {
        val cave = Cave.fromStartSituation(listOf(listOf(9, 1), listOf(1, 1)))
        val topLeft = cave.octopusses[0]
        assertEquals(3, cave.findAdjecent(topLeft).count())
    }

    @Test
    fun flashPerNeighbour() {
        val cave = Cave.fromStartSituation(
            listOf(
                listOf(9, 9, 9),
                listOf(9, 1, 9),
                listOf(9, 9, 9),
            )
        )
        println(cave)
        assertEquals(9, cave.moveDay(1).flashes)
        println(cave)
        assertEquals(9, cave.moveDay(1).flashes)
    }

    @Test
    fun fullTest() {
        val cave = Cave.fromStartSituation(
            listOf(
                listOf(1, 1, 1, 1, 1),
                listOf(1, 9, 9, 9, 1),
                listOf(1, 9, 1, 9, 1),
                listOf(1, 9, 9, 9, 1),
                listOf(1, 1, 1, 1, 1)
            )
        )
        assertEquals(9, cave.moveDay(1).flashes)
        assertEquals(9, cave.moveDay(1).flashes)
    }


    @Test
    fun bigAss100DayTest() {
        val cave = Cave.fromStartSituation(
            listOf(
                listOf(5, 4, 8, 3, 1, 4, 3, 2, 2, 3),
                listOf(2, 7, 4, 5, 8, 5, 4, 7, 1, 1),
                listOf(5, 2, 6, 4, 5, 5, 6, 1, 7, 3),
                listOf(6, 1, 4, 1, 3, 3, 6, 1, 4, 6),
                listOf(6, 3, 5, 7, 3, 8, 5, 4, 7, 8),
                listOf(4, 1, 6, 7, 5, 2, 4, 6, 4, 5),
                listOf(2, 1, 7, 6, 8, 4, 1, 7, 2, 1),
                listOf(6, 8, 8, 2, 8, 8, 1, 1, 3, 4),
                listOf(4, 8, 4, 6, 8, 4, 8, 5, 5, 4),
                listOf(5, 2, 8, 3, 7, 5, 1, 5, 2, 6)
            )
        )
        println(cave)
        assertEquals(0, cave.moveDay(1).flashes, cave.toString())
        println(cave)
        assertEquals(35, cave.moveDay(1).flashes, cave.toString())
        println(cave)
        assertEquals(204, cave.moveDay(8).flashes, cave.toString())
        println(cave)
        assertEquals(1656, cave.moveDay(90).flashes, cave.toString())
    }
}


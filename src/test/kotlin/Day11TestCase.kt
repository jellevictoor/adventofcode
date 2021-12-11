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
        val cave = Cave.fromStartSituation(listOf(listOf(9, 1), listOf(1, 1)))
        val nextDayCave = cave.moveDay(1)
        assertEquals(1, nextDayCave.flashes)
        assertEquals(3, nextDayCave.octopusses.count { it.charge == 2 })
    }
}

class DumboOctopus(val x: Int, val y: Int, val charge: Int, val flash: Boolean) {

    fun increaseCharge(): DumboOctopus {
        if (charge + 1 > 9) {
            return DumboOctopus(x, y, 0, true)
        } else {
            return DumboOctopus(x, y, charge + 1, false)
        }
    }
}

class Cave(val day: Int, val flashes: Int, val octopusses: List<DumboOctopus>) {
    companion object {
        fun fromStartSituation(startPosition: List<List<Int>>): Cave {
            val octopusses = mutableListOf<DumboOctopus>()
            for (i in startPosition.indices) {
                for (j in startPosition[i].indices) {
                    octopusses.add(DumboOctopus(i, j, startPosition[i][j], false))
                }
            }
            return Cave(0, 0, octopusses)
        }
    }

    fun moveDay(day: Int = 1): Cave {
        var cave: Cave = this
        for (i in 1..day) {
            val newOctopusses = cave.octopusses.map { it.increaseCharge() }
            val newFlashes = newOctopusses.count { it.flash }
            cave = Cave(cave.day + 1, cave.flashes + newFlashes, newOctopusses)
        }
        return cave
    }
}

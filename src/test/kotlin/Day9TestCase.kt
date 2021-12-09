import org.junit.Assert
import org.junit.Test

class Day9TestCase {
    @Test
    fun findLowPoints() {
        val actual: List<Int> = HeightMap.from(
            listOf(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678"
            )
        ).getLowPoints()
        val expected = listOf(1, 0, 5, 5)
        Assert.assertEquals(expected.sorted(), actual.sorted())
    }
}
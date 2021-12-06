import org.junit.Assert
import org.junit.Test

class Day5TestCase {
    @Test
    fun validateInput() {
        val input = listOf(
            "0,9 -> 5,9",
            "8,0 -> 0,8",
            "9,4 -> 3,4",
            "2,2 -> 2,1",
            "7,0 -> 7,4",
            "6,4 -> 2,0",
            "0,9 -> 2,9",
            "3,4 -> 1,4",
            "0,0 -> 8,8",
            "5,5 -> 8,2"
        )
        val expected = 12
        val actual = SeaMap.from(input).getNoOfDangerousAreas(2)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun validateHorizontalLine() {
        val input = Trajectory(Point(1, 1), Point(1, 3))
        val expected = listOf(Point(1, 1), Point(1, 2), Point(1, 3))
        val actual = input.getPoints()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun validateHorizontalLineBackwards() {
        val input = Trajectory(Point(9, 7), Point(7, 7))
        val expected = listOf(Point(9, 7), Point(8, 7), Point(7, 7))
        val actual = input.getPoints()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun validateDiagonalLine() {
        val input = Trajectory(Point(1, 1), Point(3, 3))
        val expected = listOf(Point(1, 1), Point(2, 2), Point(3, 3))
        val actual = input.getPoints()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun validateDiagonalLineBackwards() {
        val input = Trajectory(Point(9, 7), Point(7, 9))
        val expected = listOf(Point(7, 9), Point(8, 8), Point(9, 7)).reversed()
        val actual = input.getPoints()
        Assert.assertEquals(expected, actual)
    }
}
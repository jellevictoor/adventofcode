import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class day3TestCase {
    @Test
    fun gammaRateTestCase(){
        val input = listOf("00100",
                "11110",
                "10110",
                "10111",
                "10101",
                "01111",
                "00111",
                "11100",
                "10000",
                "11001",
                "00010",
                "01010")
        val expected = PowerConsumptionCalculator().powerConsumption(input).gamma
        Assert.assertEquals(22,expected)
    }
    @Test
    fun epsilonRateTestCase(){
        val input = listOf("00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010")
        val expected = PowerConsumptionCalculator().powerConsumption(input).epsilon
        Assert.assertEquals(9,expected)
    }
    @Test
    fun oxygenRatingTest(){
        val input = listOf("00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010")
        val expected = LifeSupportCalculator().lifeSupport(input)
        Assert.assertEquals(23,expected.oxygenRating)
    }
    @Test
    fun co2RatingTest(){
        val input = listOf("00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010")
        val expected = LifeSupportCalculator().lifeSupport(input)
        Assert.assertEquals(10,expected.co2Scrubber)
    }
    @Test
    fun testTranspose(){
        val input = listOf(
            listOf(1,2),
            listOf(3,4)
        )
        val expected = listOf(
            listOf(1,3),
            listOf(2,4)
        )
        val actual = transpose(input)
        assertEquals(expected,actual)
    }
    @Test
    fun mostFrequentBit(){
        val input = listOf(1,1,1,0,0)
        val expected = 1;
        assertEquals(expected,getMostFrequentBit(input))

    }
}
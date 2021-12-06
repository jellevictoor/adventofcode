import org.junit.Assert
import org.junit.Test

class Day6TestCase {
    @Test
    fun exampleTest(){
        val input = listOf(3,4,3,1,2).map{Lanternfish(it)}
        val actual = SchoolSimulator(input,18).calculatePopulation()
        Assert.assertEquals(26,actual)
    }
    @Test
    fun exampleTest2(){
        val input = listOf(3,4,3,1,2).map{Lanternfish(it)}
        val actual = SchoolSimulator(input,80).calculatePopulation()
        Assert.assertEquals(5934,actual)
    }
}
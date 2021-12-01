import org.junit.Test
import kotlin.test.assertEquals

class CounterTestCase {
    @Test
    fun countTestCase() {
        val input = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
        val expected = 7
        val actual = Counter().count(input)
        assertEquals<Int>(expected, actual)
    }
    @Test
    fun slidingCountTestCase(){
        val input = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
        val expected = 5
        val actual = SlidingCounter().count(input)
        assertEquals<Int>(expected, actual)
    }
}
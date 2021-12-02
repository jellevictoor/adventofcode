import org.junit.Test
import kotlin.test.assertEquals

class FileCounterTestCase {
    @Test
    fun countInFile() {
        val actual = FileProcessor("testFile",Counter()).count()
        val expected = 7
        assertEquals<Int>(expected, actual)
    }
}
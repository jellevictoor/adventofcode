import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class FileCounterTestCase {
    @Test
    fun countInFile() {
        val actual = FileCounter("testFile").count()
        val expected = 7
        assertEquals<Int>(expected, actual)
    }
}
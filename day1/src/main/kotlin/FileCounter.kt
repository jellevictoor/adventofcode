import java.io.File
import java.util.stream.Collectors

class FileCounter(fileName: String, counter: Countable) {
    private val file = File(ClassLoader.getSystemResource(fileName).file)
    private val counter = counter
    fun count(): Int {
        val list =
            file.readLines().map { line -> Integer.parseInt(line) }
        return counter.count(list)
    }
}
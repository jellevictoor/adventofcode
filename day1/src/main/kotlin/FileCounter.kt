import java.io.File
import java.util.stream.Collectors

class FileCounter(fileName: String) {
    val file = File(ClassLoader.getSystemResource(fileName).file)
    fun count(): Int {
        val list =
            file.readLines().map { line -> Integer.parseInt(line) }
        return count(list)
    }
}
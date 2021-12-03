import java.io.File

class FileProcessor(fileName: String, private val counter: SolutionExecutor) {
    private val file = File(ClassLoader.getSystemResource(fileName).file)
    fun count(): Int {
        val list = file.readLines()
        return counter.process(list)
    }
}
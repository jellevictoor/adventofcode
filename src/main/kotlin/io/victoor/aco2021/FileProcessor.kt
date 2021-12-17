package io.victoor.aco2021

import java.io.File

class FileProcessor(fileName: String, private val counter: SolutionExecutor) {
    private val file = File(ClassLoader.getSystemResource(fileName).file)
    fun count(): Number {
        val list = file.readLines()
        return counter.process(list)
    }
}
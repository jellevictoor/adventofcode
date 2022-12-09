package io.victoor.aco2022

import io.victoor.aoc.SolutionExecutor

class Day7 : SolutionExecutor {
    companion object {
        var total = 0
    }

    override fun process(input: List<String>): Number {
        val filesystem = exploreFileSystem(input)
        return filesystem.findAllDirsWithLessThan(100000)
    }

    private fun isInstruction(it: String): Boolean = it.startsWith("$")

    private fun isMovingDirectory(line: String): Boolean = line.startsWith("$ cd")

    fun exploreFileSystem(input: List<String>): File {
        var currentFile = File(null, ".", true, 0, mutableListOf())
        for (line in input.drop(1)) {
            val splittedLine = line.split(" ")
            if (isInstruction(line)) {
                if (isMovingDirectory(line)) {
                    val dirToMoveTo = splittedLine[2]
                    currentFile = if (dirToMoveTo == "..") {
                        currentFile.parent!!
                    } else {
                        currentFile.fileContent.first { it.name == dirToMoveTo }
                    }
                }

            } else {
                if (splittedLine[0] == "dir") {
                    if (isFileMissing(currentFile, splittedLine[1])) {
                        currentFile.fileContent.add(File(currentFile, splittedLine[1], true, 0, mutableListOf()))
                    }
                } else {
                    if (isFileMissing(currentFile, splittedLine[1])) {
                        val file = File(currentFile, splittedLine[1], false, splittedLine[0].toInt(), mutableListOf())
                        currentFile.fileContent.add(file)
                        currentFile.fileSize += file.fileSize
                    }
                }

            }
        }

        while (currentFile.parent != null) {
            currentFile = currentFile.parent!!
        }
        print(traverse(currentFile))
        println()
        return currentFile
    }

    private fun isFileMissing(currentFile: File, filename: String) =
        currentFile.fileContent.count { it.name == filename } == 0

    private fun traverse(it: File): String {
        return if (it.isDirectory) {
            it.fileContent.map { traverse(it) }.joinToString("\n")
        } else {
            it.toString() + " " + it.fileSize
        }
    }

    private fun newFolder(currentFile: File, currentDirectory: String) =
        File(currentFile, currentDirectory, true, 0, mutableListOf())
}

data class File(
    val parent: File?,
    val name: String,
    val isDirectory: Boolean,
    var fileSize: Int,
    val fileContent: MutableList<File>
) {
    fun calcFileSize(): Int {
        return fileContent.sumOf {
            if (it.isDirectory) {
                it.calcFileSize()
            } else {
                it.fileSize
            }
        }
    }


    fun findAllDirsWithLessThan(i: Int): Int {
        return fileContent
            .sumOf {
                val calcFileSize = it.calcFileSize()
                val i1 = if (it.isDirectory && calcFileSize < i) {
                    println("adding $it with $calcFileSize")
                    calcFileSize
                } else {
                    0
                }
                i1 +  it.findAllDirsWithLessThan(i)

            }
    }

    override fun toString(): String {
        return (parent?.toString() ?: "") + name + (if (isDirectory) "/" else "")
    }
}
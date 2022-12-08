package io.victoor.aco2022

import io.victoor.aoc.SolutionExecutor

class Day7 : SolutionExecutor {
    companion object {
        var total = 0
    }

    override fun process(input: List<String>): Number {
        val filesystem = exploreFileSystem(input)
        filesystem.findAllDirsWithLessThan(100000)
        return Day7.total
    }

    private fun isInstruction(it: String): Boolean = it.startsWith("$")

    private fun isMovingDirectory(line: String): Boolean = line.startsWith("$ cd")

    fun exploreFileSystem(input: List<String>): File {
        var currentFile = File(null, ".", true, 0, mutableListOf())
        for (line in input.drop(1)) {
            if (isInstruction(line)) {
                if (isMovingDirectory(line)) {
                    val cdMarker = "$ cd "
                    val directoryToNavigateTo = line.replace(cdMarker, "")
                    currentFile = if (directoryToNavigateTo.contains("..")) {
                        currentFile.parent!!
                    } else {
                        currentFile.fileContent.first { it.name == directoryToNavigateTo }
                    }
                }
            } else {
                println("adding ${line} to ${currentFile.name}")
                val dirMarker = "dir "
                val toAdd = if (line.startsWith(dirMarker)) {
                    newFolder(currentFile, line.replace(dirMarker, ""))
                } else {
                    val split = line.split(" ")
                    File(currentFile, split[1], false, split[0].toInt(), mutableListOf())
                }
                if (currentFile.fileContent.count { it.name == toAdd.name } == 0) {
                    currentFile.fileContent.add(toAdd)
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

    private fun traverse(it: File): String {
        return if (it.isDirectory) {
            it.fileContent.map { traverse(it) }.joinToString("\n")
        } else {
            it.toString()
        }
    }

    private fun newFolder(currentFile: File, currentDirectory: String) =
        File(currentFile, currentDirectory, true, 0, mutableListOf())
}

data class File(
    val parent: File?,
    val name: String,
    val isDirectory: Boolean,
    val fileSize: Int,
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
        return fileContent.sumOf {
            if (it.isDirectory) {
                val calcFileSize = it.findAllDirsWithLessThan(i)
                if (calcFileSize > i) {
                    0
                } else {
                    println("Filesize for ${it} is $calcFileSize")
                    Day7.total += calcFileSize
                    calcFileSize
                }
            } else {
                it.fileSize
            }
        }
    }

    override fun toString(): String {
        return (parent?.toString() ?: "") + name + (if (isDirectory) "/" else "")
    }
}
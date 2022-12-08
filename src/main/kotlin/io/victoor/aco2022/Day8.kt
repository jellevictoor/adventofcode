package io.victoor.aco2022

import io.victoor.aoc.SolutionExecutor

class Day8 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        val forest = Forest.init(input.size, input.first().toList().size)
        for (i in input.withIndex()) {
            for (j in input[i.index].toList().withIndex()) {
                forest.trees[i.index][j.index] = Tree(j.value.digitToInt())
            }
        }
        return forest.notVisibleTrees()
    }
}

data class Tree(val height: Int)
data class Forest(val trees: MutableList<MutableList<Tree>>) {
    fun notVisibleTrees(): Number {
        var visibleTrees = mutableListOf<Tree>()
        for (currentRow in trees.withIndex()) {
            for (currentColumn in trees[currentRow.index].withIndex()) {
                val column = trees.map { it[currentColumn.index] }
                val row = trees[currentRow.index]
                val tree = currentColumn.value
                if ((row.subList(0, currentColumn.index).maxOfOrNull { it.height } ?: -1) < tree.height ||
                    (row.subList(currentColumn.index+1, trees.size).maxOfOrNull { it.height } ?: -1) < tree.height ||
                    (column.subList(0, currentRow.index).maxOfOrNull { it.height } ?: -1) < tree.height ||
                    (column.subList(currentRow.index+1, trees.size).maxOfOrNull { it.height } ?: -1) < tree.height
                ) {
                    println("tree at position ${currentRow.index},${currentColumn.index} has size $tree is visible")
                    visibleTrees.add(tree)
                }else{
                    println("tree at position ${currentRow.index},${currentColumn.index} has size $tree is hidden")

                }
            }
        }

        return visibleTrees.size
    }

    companion object {
        fun init(height: Int, width: Int): Forest {
            val trees = mutableListOf<MutableList<Tree>>()
            for (i in 0 until height) {
                trees.add(mutableListOf())
                for (j in 0 until width) {
                    trees[i].add(Tree(-1))
                }
            }
            return Forest(trees)
        }
    }
}
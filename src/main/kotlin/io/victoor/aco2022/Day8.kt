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
        var visibleTrees = mutableListOf<Int>()
        for (currentRow in trees.withIndex()) {
            for (currentColumn in trees[currentRow.index].withIndex()) {
                val column = trees.map { it[currentColumn.index] }
                val row = trees[currentRow.index]
                val tree = currentColumn.value

                val leftView = row.subList(0, currentColumn.index).reversed()
                val indexOfFirstLargerTreeLeft = leftView.indexOfFirst { it.height >= tree.height }
                val left = if (indexOfFirstLargerTreeLeft == -1) {
                    currentColumn.index
                } else {
                    indexOfFirstLargerTreeLeft + 1
                }

                val rightView = row.subList(currentColumn.index + 1, row.size)
                val indexOfFirstLargerTreeRight = rightView.indexOfFirst { it.height >= tree.height }
                val right = if (indexOfFirstLargerTreeRight == -1) {
                    column.size - 1 - currentColumn.index
                } else {
                    indexOfFirstLargerTreeRight + 1
                }

                val topView = column.subList(0, currentRow.index).reversed()
                val indexOfFirstLargerTreeTop = topView.indexOfFirst { it.height >= tree.height }
                val top = if (indexOfFirstLargerTreeTop == -1) {
                    currentRow.index
                } else {
                    indexOfFirstLargerTreeTop + 1
                }

                val bottomView = column.subList(currentRow.index + 1, row.size)
                val indexOfFirstLargerTreeBottom = bottomView.indexOfFirst { it.height >= tree.height }
                val bottom = if (indexOfFirstLargerTreeBottom == -1) {
                    row.size - 1 - currentRow.index
                } else {
                    indexOfFirstLargerTreeBottom + 1
                }

                val product = left * right * top * bottom
                visibleTrees.add(product)
                println("tree at ${currentRow.index},${currentColumn.index} has a $left * $right * $bottom * $top score of $product")
            }
        }

        return visibleTrees.maxOf { it }
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
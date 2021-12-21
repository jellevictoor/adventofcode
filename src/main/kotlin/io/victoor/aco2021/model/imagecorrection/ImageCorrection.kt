package io.victoor.aco2021.model.imagecorrection

import io.victoor.aco2021.model.Grid
import io.victoor.aco2021.model.Point

class ImageCorrection(val encoding: String) {

    fun pass(input: Grid): Grid {
        println("before")
        println(draw(input.grow(1)))
        val expandedNodes = input
            .grow(1)
            .nodes
            .map { Pair(it, input.infiniteAdjecentLocations(it)) }
            .map { Point(it.first.x, it.first.y, decode(it.second)) }
        val expandedGrid = Grid(expandedNodes)
        println("after")
        println(draw(expandedGrid))

        return expandedGrid

    }

    private fun draw(expandedGrid: Grid): String {
        return expandedGrid.nodeLookup
            .joinToString("\n") {
                it.joinToString("") {
                    when (it.value) {
                        1 -> "#"
                        else -> "."
                    }
                }
            }
    }

    fun decode(points: List<Point>): Int {
        val binary = points.fold("") { tot, acc -> tot + acc.value }
        if(binary.length!=9){
            throw IllegalStateException("binary must always be 9 chars at $points[4]")
        }
        val index = binary.toInt(2)
        return when (encoding.get(index)) {
            '#' -> 1
            '.' -> 0
            else -> -1
        }
    }


}

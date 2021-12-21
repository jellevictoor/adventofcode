package io.victoor.aco2021.model.imagecorrection

import io.victoor.aco2021.model.Grid
import io.victoor.aco2021.model.Point

class ImageCorrection(val encoding: String) {

    fun pass(input: Grid, times: Int): Grid {
        var image = input
        repeat(times) {
            val boundaryValue = getBoundaryValue(it)
            val expandedImage = image.grow(1, boundaryValue)
            val expandedNodes = expandedImage
                .nodes
                .map { Pair(it, expandedImage.infiniteAdjecentLocations(it, boundaryValue)) }
                .map { Point(it.first.x, it.first.y, decode(it.second)) }
            image = Grid(expandedNodes)
        }

        return image

    }

    private fun getBoundaryValue(pass: Int): Int {
        val boundaryChar = when (encoding.first() == '#') {
            true -> if (pass % 2 == 0) encoding.last() else encoding.first()
            false -> '.'
        }

        return when (boundaryChar) {
            '#' -> 1
            else -> 0
        }
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
        if (binary.length != 9) {
            throw IllegalStateException("binary must always be 9 chars at $points[4]")
        }
        val index = binary.toInt(2)
        return econdingByIndex(index)
    }

    private fun econdingByIndex(index: Int): Int {
        return when (encoding.get(index)) {
            '#' -> 1
            '.' -> 0
            else -> -1
        }
    }


}

package io.victoor.aco2021

import io.victoor.aco2021.model.Grid
import io.victoor.aco2021.model.imagecorrection.ImageCorrection

class Day20 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        val imageCorrection = ImageCorrection(input[0])
        val inputImage = Grid.fromLights(input.drop(2))
        val result = imageCorrection.pass(inputImage, 50)
        return result.nodes.sumOf { it.value }
    }

}
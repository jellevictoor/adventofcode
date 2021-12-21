package io.victoor.aco2021

import io.victoor.aco2021.model.Grid
import io.victoor.aco2021.model.Point
import io.victoor.aco2021.model.imagecorrection.ImageCorrection
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.hasItems
import org.junit.Assert.assertThat
import org.junit.Test

class Day20TestCase {
    val encoding = ("..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##\n" +
            "#..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###\n" +
            ".######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#.\n" +
            ".#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#.....\n" +
            ".#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#..\n" +
            "...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.....\n" +
            "..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#").replace("\n", "")

    @Test
    fun correctImage() {
        val input: List<String> = ("...\n" + "#..\n" + ".#.").split("\n").toList()
        val originalInput = Grid.fromLights(input)
        val output: Grid = ImageCorrection(encoding).pass(originalInput, 1)
        assertThat(1, CoreMatchers.`is`(output.lookup(1, 1).value))
    }

    @Test
    fun growCorrectImage() {
        val input: List<String> = ("...\n" + "#..\n" + ".#.").split("\n").toList()
        val originalInput = Grid.fromLights(input)
        val expected = originalInput.nodes.count { it.value == 1 }
        val actual = originalInput.grow(1, 0).nodes.count { it.value == 1 }
        assertThat(actual, CoreMatchers.`is`(expected))
    }

    @Test
    fun goldenMaster() {
        val input = ("#..#.\n" +
                "#....\n" +
                "##..#\n" +
                "..#..\n" +
                "..###").split("\n").toList()
        val originalInput = Grid.fromLights(input)
        val imageCorrection = ImageCorrection(encoding)
        val output: Grid = imageCorrection.pass(originalInput, 2)
        assertThat(35, CoreMatchers.`is`(output.nodes.count { it.value == 1 }))

    }

    @Test
    fun decode() {
        val points = ("...#...#.".replace(".", "0").replace("#", "1"))
            .mapIndexed { index, c -> Point(index, 0, Character.getNumericValue(c)) }

        val actual = ImageCorrection(encoding).decode(points)
        assertThat(actual, CoreMatchers.`is`(1))

    }

    @Test
    fun infiniteAdjectentLocations() {
        val input: List<String> = ("...\n" + "#..\n" + ".#.").split("\n").toList()
        val originalInput = Grid.fromLights(input)
        val infiniteAdjecentLocations = originalInput.infiniteAdjecentLocations(Point(1, 1), 0)
        assertThat(
            infiniteAdjecentLocations, CoreMatchers.hasItems(
                Point(0, 0, 0),
                Point(1, 0, 0),
                Point(2, 0, 0),
                Point(0, 1, 1),
                Point(1, 1, 0),
                Point(2, 1, 0),
                Point(0, 2, 0),
                Point(1, 2, 1),
                Point(2, 2, 0)
            )
        )

    }

    @Test
    fun infiniteAdjectentLocationsNotExisting() {
        val input: List<String> = ("...\n" + "#..\n" + ".#.").split("\n").toList()
        val originalInput = Grid.fromLights(input)
        val infiniteAdjecentLocations = originalInput.infiniteAdjecentLocations(Point(0, 0), 0)
        assertThat(
            infiniteAdjecentLocations, CoreMatchers.hasItems(
                Point(-1, -1, 0),
                Point(0, -1, 0),
                Point(1, -1, 0),
                Point(-1, 0, 0),
                Point(0, 0, 0),
                Point(1, 0, 0),
                Point(-1, 1, 0),
                Point(0, 1, 1),
                Point(1, 1, 0),
            )
        )
    }

    @Test
    fun infiniteAdjectentLocationsExisting() {
        val input: List<String> = ("...\n" + "#..\n" + ".#.").split("\n").toList()
        val originalInput = Grid.fromLights(input)
        val infiniteAdjecentLocations = originalInput.infiniteAdjecentLocations(Point(1, 1), 0)
        assertThat(
            infiniteAdjecentLocations, hasItems(
                Point(0, 0, 0),
                Point(1, 0, 0),
                Point(2, 0, 0),
                Point(0, 1, 1),
                Point(1, 1, 0),
                Point(2, 1, 0),
                Point(0, 2, 0),
                Point(1, 2, 1),
                Point(2, 2, 0)
            )
        )
    }

    @Test
    fun growTest() {
        val grow = Grid.fromLights(listOf("#")).grow(1, 0)
        assertThat(
            grow.nodes, hasItems(
                Point(0, 0, 0),
                Point(1, 0, 0),
                Point(2, 0, 0),
                Point(0, 1, 0),
                Point(1, 1, 1),
                Point(2, 1, 0),
                Point(0, 2, 0),
                Point(1, 2, 0),
                Point(2, 2, 0)
            )
        )
    }
}
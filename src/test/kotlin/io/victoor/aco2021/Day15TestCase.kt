package io.victoor.aco2021
import org.junit.Test
import kotlin.test.assertEquals

class Day15TestCase {
    @Test
    fun navigate() {
        val input = listOf(
            "1163751742",
            "1381373672",
            "2136511328",
            "3694931569",
            "7463417111",
            "1319128137",
            "1359912421",
            "3125421639",
            "1293138521",
            "2311944581"
        )
        val from = HeightMap.Grid.from(input)
        val shortestPath = Dijkstra(from).shortestPath(HeightMap.Point(0, 0, 1), HeightMap.Point(9, 9, 1))
        assertEquals(40,shortestPath)
    }
    @Test
    fun navigate5Time() {
        val input = listOf(
            "1163751742",
            "1381373672",
            "2136511328",
            "3694931569",
            "7463417111",
            "1319128137",
            "1359912421",
            "3125421639",
            "1293138521",
            "2311944581"
        )
        val from = HeightMap.Grid.from(input).expand(5)
        val shortestPath = Dijkstra(from).shortestPath(HeightMap.Point(0, 0, 1), HeightMap.Point(49, 49, 1))
        assertEquals(315,shortestPath)
    }
    @Test
    fun expand() {
        val input = listOf(
            "11",
            "13"
        )
        val from = HeightMap.Grid.from(input).expand(2)
        assertEquals(16,from.nodes.size)
    }
    @Test
    fun expandSuperSmall() {
        val input = listOf(
            "1"
        )
        val from = HeightMap.Grid.from(input).expand(2)
        assertEquals(4,from.nodes.size)
    }
}
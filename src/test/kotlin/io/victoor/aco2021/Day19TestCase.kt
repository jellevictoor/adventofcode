package io.victoor.aco2021

import org.junit.Test

class Day19TestCase {
    @Test
    fun exampleCase() {
        val scanners = listOf(
            Scanner(
                listOf(
                    Beacon(Point3d(0, 2, 0)),
                    Beacon(Point3d(4, 1, 0)),
                    Beacon(Point3d(3, 3, 0))
                )
            ), Scanner(
                listOf(
                    Beacon(Point3d(-1, -1, 0)),
                    Beacon(Point3d(-5, 0, 0)),
                    Beacon(Point3d(-2, 1, 0))
                )
            )
        )

    }
}
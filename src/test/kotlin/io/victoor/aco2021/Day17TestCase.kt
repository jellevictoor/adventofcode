package io.victoor.aco2021

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day17TestCase {
    @Test
    fun trajectorySimulation() {
        val initialPosition = Point(0, 0)
        val probe = Probe(initialPosition, 7, 2)
        val launcher = ProbeLauncher(probe, Target(Point(20, -10), Point(30, -5)))
        val simulate = launcher.simulate()
        println(simulate)
        assertTrue(launcher.isInTarget())
    }
    @Test
    fun trajectorySimulationOther() {
        val initialPosition = Point(0, 0)
        val probe = Probe(initialPosition, 6,3)
        val launcher = ProbeLauncher(probe, Target(Point(20, -10), Point(30, -5)))
        val simulate = launcher.simulate()
        println(simulate)
        assertTrue(launcher.isInTarget())
    }
    @Test
    fun trajectorySimulationOtherOne() {
        val initialPosition = Point(0, 0)
        val probe = Probe(initialPosition, 9,0)
        val launcher = ProbeLauncher(probe, Target(Point(20, -10), Point(30, -5)))
        val simulate = launcher.simulate()
        println(simulate)
        assertTrue(launcher.isInTarget())
    }

    @Test
    fun withinPointTest() {
        val point = Point(0, 10)
        val target = Target(Point(-10, 9), Point(5, 11))
        assertTrue(target.contains(point))
    }

    @Test
    fun notWithinPointTest() {
        val point = Point(11, 0)
        val target = Target(Point(-10, 10), Point(-5, 11))
        assertFalse(target.contains(point))
    }
    @Test
    fun testSimulation(){
        val bestSettings = ProbeSimulator().findBestProbeSettings(Target(Point(20, -10), Point(30, -5)))
        assertEquals(112, bestSettings.distinct().size)
    }
}
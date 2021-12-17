package io.victoor.aco2021
import org.junit.Test
import kotlin.test.assertEquals

class Day14TestCase {
    val pairs = listOf(
        Pair("CH", "B"),
        Pair("HH", "N"),
        Pair("CB", "H"),
        Pair("NH", "C"),
        Pair("HB", "C"),
        Pair("HC", "B"),
        Pair("HN", "C"),
        Pair("NN", "C"),
        Pair("BH", "H"),
        Pair("NC", "B"),
        Pair("NB", "B"),
        Pair("BN", "B"),
        Pair("BB", "N"),
        Pair("BC", "B"),
        Pair("CC", "N"),
        Pair("CN", "C")
    )

    @Test
    fun polymerTestCase() {
        val frequencyMap = mapOf(
            Pair("N", CharCounter(2)),
            Pair("C", CharCounter(2)),
            Pair("B", CharCounter(2)),
            Pair("H", CharCounter(1))
        )
        assertEquals(
            frequencyMap, Polymer("NNCB", pairs).apply(1).elementCount
        )
    }

    @Test
    fun polymerTestCaseshort() {
        val frequencyMap = mapOf(
            Pair("N", CharCounter(2)),
            Pair("C", CharCounter(1)),
            Pair("B", CharCounter(0)),
            Pair("H", CharCounter(0))
        )
        assertEquals(
            frequencyMap, Polymer("NN", pairs).apply(1).elementCount
        )
    }

    @Test
    fun polymerTestCases2Iterations() {
        val frequencyMap = countChars("NBCCNBBBCBHCB")
        assertEquals(
            frequencyMap, Polymer("NNCB", pairs).apply(2).elementCount
        )
    }
    @Test
    fun polymerTestCaseshort2Iterations() {
        val frequencyMap = countChars("NBCCN")
        assertEquals(
            frequencyMap, Polymer("NN", pairs).apply(2).elementCount
        )
    }

    private fun countChars(input: String) =
        input.toCharArray().toList().groupingBy { it }.eachCount().map { Pair(it.key.toString(), CharCounter(it.value.toLong())) }.toMap()

    @Test
    fun polymerTestCaseshort3() {
        val frequencyMap = mapOf(
            Pair("N", CharCounter(2)),
            Pair("C", CharCounter(2)),
            Pair("B", CharCounter(1)),
            Pair("H", CharCounter(0))
        )
        //NCNBC
        assertEquals(
            frequencyMap, Polymer("NNC", pairs).apply(1).elementCount
        )
    }

    @Test
    fun tenOperations() {
        assertEquals(1588, Polymer("NNCB", pairs).apply(10).score())

    }
}
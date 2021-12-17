package io.victoor.aco2021
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

public class Day8TestCase {
    @Test
    fun testEncodingRegister() {
        val input = listOf(
            "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | " +
                    "fdgacbe cefdb cefbgd gcbe",
            "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | " +
                    "fcgedb cgb dgebacf gc",
            "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | " +
                    "cg cg fdcagb cbg",
            "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | " +
                    "efabcd cedba gadfec cb",
            "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | " +
                    "gecf egdcabf bgf bfgea",
            "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | " +
                    "gebdcfa ecba ca fadegcb",
            "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | " +
                    "cefg dcbef fcge gbcadfe",
            "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | " +
                    "ed bcgafe cdgba cbgef",
            "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | " +
                    "gbdfcae bgc cg cgb",
            "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | " +
                    "fgae cfgab fg bagce"
        )
        val sum = input
            .map { Display(it) }
            .map { it.decodedDigits() }
            .sum()
        Assert.assertEquals(61229, sum)
    }




    @Test
    fun testEncodingRegisterSpecificCase() {
        val input = listOf(
            "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | " +
                    "fcgedb cgb dgebacf gc"
        )
        val sum = input
            .map { Display(it) }
            .map { it.decodedDigits() }
            .first()

        Assert.assertEquals(9781, sum)
    }
    @Test
    fun testEncodingRegisterSpecific() {
        val input = listOf(
            "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | " +
                    "cdfeb fcadb cdfeb cdbaf"
        )
        val sum = input
            .map { Display(it) }
            .map { it.decodedDigits() }
            .first()

        Assert.assertEquals(5353, sum)
    }
    @Test
    fun findSeven() {
        val input =
            "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(" ")
        val seven = Encoding(input).findSeven();
        Assert.assertEquals(normalize("dab"), seven.stringMapping)
    }

    @Test
    fun findFour() {
        val input =
            "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(" ")
        val seven = Encoding(input).findFour();
        Assert.assertEquals(normalize("eafb"), seven.stringMapping)
    }

    @Test
    fun findEight() {
        val input =
            "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(" ")
        val eight = Encoding(input).findEight();
        Assert.assertEquals(normalize("acedgfb"), eight.stringMapping)
    }

    @Test
    fun findOne() {
        val input =
            "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(" ")
        val one = Encoding(input).findOne();
        Assert.assertEquals(normalize("ab"), one.stringMapping)
    }
}
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class Day10TestCase {
    @Test
    fun incompleteLine() {
        val actual = Line("[(()[<>])]({[<{<<[]>>(").isValid()
        val expected = 0
        assertEquals(expected, actual)
    }

    @Test
    fun incorrectLine() {
        val actual = Line("{([(<{}[<>[]}>{[]{[(<()>").isValid()
        val expected = 1197
        assertEquals(expected, actual)
    }

    @Test
    fun simpleCorrectLines() {
        assertEquals(0, Line("([])").isValid())
        assertEquals(0, Line("{()()()}").isValid())
        assertEquals(0, Line("<([{}])>").isValid())
        assertEquals(0, Line("[<>({}){}[([])<>]]").isValid())
        assertEquals(0, Line("(((((((((())))))))))").isValid())
    }

    @Test
    fun corruptLine() {
        val actual = Line("{([(<{}[<>[]}>{[]{[(<()>").isValid()
        val expected = 1197
        assertEquals(expected, actual)
    }

    @Test
    fun exampleCompletion() {
        assertEquals(288957, Line("[({(<(())[]>[[{[]{<()<>>").completeLine())
        assertEquals(5566, Line("[(()[<>])]({[<{<<[]>>(").completeLine())
        assertEquals(1480781, Line("(((({<>}<{<{<>}{[]{[]{}").completeLine())
        assertEquals(995444, Line("{<[[]]>}<{[{[{[]{()[[[]").completeLine())
        assertEquals(294, Line("<{([{{}}[<[[[<>{}]]]>[]]").completeLine())
    }

    @Test
    fun takeMiddleScore() {
        val actual = EncodingRunner().process(
            listOf(
                "[({(<(())[]>[[{[]{<()<>>",
                "[(()[<>])]({[<{<<[]>>(",
                "(((({<>}<{<{<>}{[]{[]{}",
                "{<[[]]>}<{[{[{[]{()[[[]",
                "<{([{{}}[<[[[<>{}]]]>[]]"
            )
        )
        assertEquals(288957, actual)
    }
}
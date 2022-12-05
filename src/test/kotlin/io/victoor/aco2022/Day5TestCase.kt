package io.victoor.aco2022;

import org.junit.Test;

public class Day5TestCase {
    @Test
    fun moveTest(){
       val input = "    [D]    \n" +
                "[N] [C]    \n" +
                "[Z] [M] [P]\n" +
                " 1   2   3 \n" +
                "\n" +
                "move 1 from 2 to 1\n" +
                "move 3 from 1 to 3\n" +
                "move 2 from 2 to 1\n" +
                "move 1 from 1 to 2"
        Day5().process(input.split("\n"))
    }
}

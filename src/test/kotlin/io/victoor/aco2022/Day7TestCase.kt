package io.victoor.aco2022

import org.junit.Assert
import org.junit.Test

class Day7TestCase {
    @Test
    fun exampleDay7() {
        val input = "\$ cd /\n" +
                "\$ ls\n" +
                "dir a\n" +
                "14848514 b.txt\n" +
                "8504156 c.dat\n" +
                "dir d\n" +
                "\$ cd a\n" +
                "\$ ls\n" +
                "dir e\n" +
                "29116 f\n" +
                "2557 g\n" +
                "62596 h.lst\n" +
                "\$ cd e\n" +
                "\$ ls\n" +
                "584 i\n" +
                "\$ cd ..\n" +
                "\$ cd ..\n" +
                "\$ cd d\n" +
                "\$ ls\n" +
                "4060174 j\n" +
                "8033020 d.log\n" +
                "5626152 d.ext\n" +
                "7214296 k"
        val day7 = Day7()
        val splitted = input.split("\n")
        val total = day7.exploreFileSystem(splitted).calcFileSize()
        Assert.assertEquals(48381165, total)
        val actual = day7.process(splitted)
        Assert.assertEquals(95437, actual)
    }
}
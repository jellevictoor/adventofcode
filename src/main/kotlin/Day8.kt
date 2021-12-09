class Decoder() : SolutionExecutor {
    override fun process(input: List<String>): Number {
        return input
            .map { Display(it) }
            .map { it.decodedDigits() }
            .sum()
    }
}

class Display(encodedDisplay: String) {
    private val encoding: Encoding
    private val encodedValues: List<String>
    fun decodedDigits(): Int {
        val decoded = encodedValues
            .map { normalize(it) }
            .map { encoding.decode(it) }
            .map { it ?: -1 }
            .joinToString("").toInt()
        println(decoded)
        return decoded

    }

    init {
        val splittedInput = encodedDisplay.split(" | ")
        encoding = Encoding(splittedInput[0].split(" "))
        encodedValues = splittedInput[1].split(" ")
    }
}

class Encoding( codedValues: List<String>) {
    private var normalizedCodedValues: List<String>
    private var position = mutableListOf<String>()
    val lookupRegistry = mutableListOf<DigitMapping>()
    fun decode(input: String): Int? =
        lookupRegistry.firstOrNull { normalize(it.stringMapping) == normalize(input) }?.intValue

    init {
        normalizedCodedValues = codedValues.map { normalize(it) }
        val one = findOne()
        val four = findFour()
        val seven = findSeven()
        val eight = findEight()
        //7 digits 8
        //6 digits 0, 6, 9
        //5 digits 2, 3, 5
        //4 digits 4
        //3 digits 7
        //2 digits 1
        val sixDigit = findPossibleSix()
        val fiveDigit = findPossibleFive()
        val six = DigitMapping(6, sixDigit.first { seven.asSet().minus(it.asSet()).isNotEmpty() }.stringMapping)
        val three = DigitMapping(3, fiveDigit.first { seven.asSet().minus(it.asSet()).isEmpty() }.stringMapping)

        val five = DigitMapping(5, fiveDigit.filter{it.stringMapping!=three.stringMapping}.first{it.asSet().minus(six.asSet()).isEmpty()}.stringMapping)
        val two = DigitMapping(2, fiveDigit.first{it.stringMapping!=three.stringMapping && it.stringMapping!=five.stringMapping}.stringMapping)

        val nine = DigitMapping(9, sixDigit.filter { it.stringMapping != six.stringMapping }.first { four.asSet().minus(it.asSet()).isEmpty() }.stringMapping)
        val zero = DigitMapping(0, sixDigit.first { it.stringMapping != six.stringMapping && it.stringMapping != nine.stringMapping }.stringMapping)
        lookupRegistry.add(zero)
        lookupRegistry.add(one)
        lookupRegistry.add(two)
        lookupRegistry.add(three)
        lookupRegistry.add(four)
        lookupRegistry.add(five)
        lookupRegistry.add(six)
        lookupRegistry.add(seven)
        lookupRegistry.add(eight)
        lookupRegistry.add(nine)
    }

    private fun findPossibleSix() = findBySize(6)
    private fun findPossibleFive() = findBySize(5)

    fun findOne(): DigitMapping = DigitMapping(1, findBySize(2).first().stringMapping)

    fun findSeven(): DigitMapping = DigitMapping(7, findBySize(3).first().stringMapping)

    fun findFour(): DigitMapping = DigitMapping(4, findBySize(4).first().stringMapping)

    fun findEight(): DigitMapping = DigitMapping(8, findBySize(7).first().stringMapping)

    private fun findBySize(expectedLength: Int) = normalizedCodedValues.filter { it.length == expectedLength }.map { DigitMapping.unknownDigitMapping(it) }

}

data class DigitMapping(val intValue: Int, val stringMapping: String) {


    fun asSet() = stringAsSet(stringMapping)

    companion object {
        fun unknownDigitMapping(mapping: String) = DigitMapping(-1, mapping)
    }
}

fun stringAsSet(denormalized: String) = denormalized.toCharArray().map { Character.toString(it) }.toSet()

fun normalize(denormalized: String) = stringAsSet(denormalized).sorted().joinToString("")
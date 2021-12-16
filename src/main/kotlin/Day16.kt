class Day16 {}
data class Package(val content: String) {
    fun binaryRepresentation(): String {
        return content.toCharArray()
            .map { Integer.toBinaryString(it.digitToInt(16)) }
            .map { String.format("%4s", it).replace(" ", "0") }
            .joinToString("")
    }

}
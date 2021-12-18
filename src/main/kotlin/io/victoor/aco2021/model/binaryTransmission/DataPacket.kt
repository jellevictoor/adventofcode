package io.victoor.aco2021.model.binaryTransmission

abstract class DataPacket(binaryRepresentation: String) {

    abstract fun versionSum(): Int
    abstract fun value(): Long

    val header = DataHeader.from(binaryRepresentation)
    val content = binaryRepresentation.substring(6)
    val rawValue = binaryRepresentation
    fun getBinaryRepresentaion(): String = rawValue
    abstract fun getLength(): Int

    companion object {
        fun fromHex(content: String): DataPacket {
            val binaryRepresentation = content.toCharArray()
                .map { Integer.toBinaryString(it.digitToInt(16)) }
                .map { String.format("%4s", it).replace(" ", "0") }
                .joinToString("")
            return fromBinaryRepresentation(binaryRepresentation)
        }

        fun fromBinaryRepresentation(binaryRepresentation: String): DataPacket {
            val type = DataHeader.from(binaryRepresentation).type
            return when (type) {
                4 -> LiteralDataPacket(binaryRepresentation)
                0 -> Operation(binaryRepresentation) { list -> list.sumOf { it.value() } }
                else -> Operation(binaryRepresentation, { it.size.toLong() })
            }
        }
    }

}
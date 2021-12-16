class Day16 {}

data class DataHeader(val version: Int, val type: Int) {
    companion object {
        fun from(binaryRepresentation: String): DataHeader {
            return DataHeader(
                Integer.parseInt(binaryRepresentation.substring(0, 3), 2),
                Integer.parseInt(binaryRepresentation.substring(3, 6), 2)
            )
        }
    }
}

data class DataPacket(val binaryRepresentation: String) {
    val type = Integer.parseInt(binaryRepresentation.substring(3, 6), 2)
    val header = DataHeader.from(binaryRepresentation)
    val content = binaryRepresentation.substring(6)
    val value: DataPacketValue

    init {
        value = when (header.type) {
            4 -> Literal(header, content)
            else -> Operation(header, content)
        }
    }

    fun length() = value.getLength() + 6 // + header

    fun value(): Int {
        return when (type) {
            4 -> Literal(header, content).intValue
            else -> Operation(header, content).getValue()
        }
    }

    fun versionSum() = value.getVersionSum()

    private fun decodeOperation(): List<Int> {
        TODO()
    }


    companion object {
        fun fromHex(content: String): DataPacket {
            val binaryRepresentation = content.toCharArray()
                .map { Integer.toBinaryString(it.digitToInt(16)) }
                .map { String.format("%4s", it).replace(" ", "0") }
                .joinToString("")

            return DataPacket(binaryRepresentation)
        }
    }
}

interface DataPacketValue {
    fun getLength(): Int
    fun getValue(): Int
    fun getVersionSum(): Int
}

data class Operation(val header: DataHeader, val value: String) : DataPacketValue {
    private val length: Int
    private val content: String
    private val list = mutableListOf<DataPacketValue>()

    init {
        val lengthIndex: Int
        if (value.substring(0, 1).toInt(2) == 0) {
            lengthIndex = 15
            length = value.substring(1, lengthIndex + 1).toInt(2)
            content = value.substring(1 + lengthIndex)
            var index = 0
            while (index < length) {
                val dataPacket = DataPacket(content.substring(index))
                index += dataPacket.length()
                list.add(dataPacket.value)
            }
        } else {
            lengthIndex = 11
            val times = value.substring(1, lengthIndex + 1).toInt(2)
            length = times * 11
            content = value.substring(1 + lengthIndex)
            var index = 0
            while (index < length) {
                val dataPacket = DataPacket(content.substring(index))
                index += 11
                list.add(dataPacket.value)
            }
        }

    }

    fun getSubPackages(): List<DataPacketValue> {
        return list
    }

    override fun getLength(): Int {
        return length
    }

    override fun getValue(): Int {
        return getSubPackages().sumOf { it.getValue() }
    }

    override fun getVersionSum(): Int {
        return header.version + getSubPackages().sumOf { it.getVersionSum() }
    }

}

data class Literal(val header: DataHeader, val content: String) : DataPacketValue {
    private val parts = content.windowed(5, 5)
    private val length: Int
    val intValue: Int

    init {
        val characters = mutableListOf<String>()
        var length = 0
        for (part in parts) {
            characters.add(part.substring(1))
            length += part.length
            if (part.toCharArray()[0] == '0') {
                break;
            }
        }
        val binaryValue = characters.joinToString("")
        intValue = Integer.parseInt(binaryValue, 2)
        this.length = length
    }

    override fun getLength() = length
    override fun getValue(): Int {
        return intValue
    }

    override fun getVersionSum(): Int {
        return header.version
    }
}

data class Transmission(val transmission: String) {
    val dataPacket = DataPacket.fromHex(transmission)
    fun getVersionSum() = dataPacket.versionSum()

}
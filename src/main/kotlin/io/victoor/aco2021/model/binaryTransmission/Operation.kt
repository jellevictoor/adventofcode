package io.victoor.aco2021.model.binaryTransmission

data class Operation(val binaryRepresentation: String, val operator: (List<DataPacket>) -> Long) : DataPacket(binaryRepresentation) {
    private val subPackages = mutableListOf<DataPacket>()
    private val lengthType: Int = this.content.substring(0, 1).toInt(2)
    private val lengthIndex: Int

    init {
        if (lengthType == 0) {
            lengthIndex = 15
            val lengthBit = content.substring(1, lengthIndex + 1).toInt(2)
            val parsableContent = content.substring(1 + lengthIndex)
            var index = 0
            while (index < lengthBit) {
                val subPackage = parsableContent.substring(index)
                val dataPacket = fromBinaryRepresentation(subPackage)
                index += dataPacket.getLength()
                subPackages.add(dataPacket)
            }
        } else {
            lengthIndex = 11
            val times = content.substring(1, lengthIndex + 1).toInt(2)
            val parsableContent = content.substring(1 + lengthIndex)
            while (subPackages.size < times) {
                val subPackage = parsableContent.substring(subPackages.sumOf { it.getLength() })
                val dataPacket = fromBinaryRepresentation(subPackage)
                subPackages.add(dataPacket)
            }
        }

    }

    fun getValue(): List<DataPacket> {
        return subPackages
    }

    override fun versionSum(): Int = header.version + subPackages.sumOf { it.versionSum() }
    override fun value(): Long = operator.invoke(subPackages.toList())
    override fun getLength(): Int {
        return header.length +
                1 + // length type
                lengthIndex +  // length
                subPackages.sumOf { it.getLength() }
    }
}

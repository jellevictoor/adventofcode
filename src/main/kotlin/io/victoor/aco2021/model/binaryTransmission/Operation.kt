package io.victoor.aco2021.model.binaryTransmission

import io.victoor.aco2021.DataPacketValue

data class Operation(val binaryRepresentation: String) : DataPacket(binaryRepresentation) {
    private val subPackages = mutableListOf<DataPacket>()

    init {
        val lengthIndex: Int
        val length: Int
        if (this.content.substring(0, 1).toInt(2) == 0) {
            lengthIndex = 15
            length = content.substring(1, lengthIndex + 1).toInt(2)
        } else {
            lengthIndex = 11
            val times = content.substring(1, lengthIndex + 1).toInt(2)
            length = times * 11
        }
        val parsableContent = content.substring(1 + lengthIndex)
        var index = 0
        while (index < length) {
            val dataPacket = DataPacket.fromBinaryRepresentation(parsableContent)
            index += dataPacket.length()
            subPackages.add(dataPacket)
        }

    }

    fun getValue(): List<DataPacket> {
        return subPackages
    }

    override fun versionSum(): Int = header.version + subPackages.sumOf { it.versionSum() }
    override fun value(): Int = subPackages.sumOf { it.value() }


}
package io.victoor.aco2021

import io.victoor.aco2021.model.binaryTransmission.DataPacket

class Day16 : SolutionExecutor {
    override fun process(input: List<String>): Number {
        return DataPacket.fromHex(input[0]).value()
    }
}


interface DataPacketValue {
    fun getLength(): Int
    fun getValue(): Int
    fun getVersionSum(): Int
}
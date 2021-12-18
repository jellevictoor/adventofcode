package io.victoor.aco2021

import io.victoor.aco2021.model.binaryTransmission.DataPacket
import org.junit.Test
import kotlin.test.assertEquals

class Day16TestCase {
    @Test
    fun decodePacket() {
        val input = DataPacket.fromHex("D2FE28")
        assertEquals("110100101111111000101000", input.getBinaryRepresentaion())
    }

    @Test
    fun decodePacketMetaData() {
        val input = DataPacket.fromHex("D2FE28")
        assertEquals(6, input.header.version)
        assertEquals(4, input.header.type)
    }

    @Test
    fun decodeLiteralPacketData() {
        val input = DataPacket.fromHex("D2FE28")
        assertEquals(2021, input.value())
    }

    @Test
    fun decodeOperationPacketData() {
        val input = DataPacket.fromHex("38006F45291200")
        assertEquals(30, input.value())
    }

    @Test
    fun decodeOperationPacketDataSecond() {
        val input = DataPacket.fromHex("EE00D40C823060")
        assertEquals(1 + 2 + 3, input.value())
    }

    @Test
    fun bigTests16() {
        assertEquals(16, DataPacket.fromHex("8A004A801A8002F478").versionSum())

    }

    @Test
    fun bigTests12() {
        assertEquals(12, DataPacket.fromHex("620080001611562C8802118E34").versionSum())

    }

    @Test
    fun bigTests23() {
        assertEquals(23, DataPacket.fromHex("C0015000016115A2E0802F182340").versionSum())

    }

    @Test
    fun bigTests31() {
        assertEquals(31, DataPacket.fromHex("A0016C880162017C3686B18A3D4780").versionSum())

    }

    @Test
    fun decodeSubPackage() {
        val input = DataPacket.fromBinaryRepresentation("11010001010").value()
        assertEquals(10, input)
    }
    @Test
    fun sumOperator(){
        val packet = DataPacket.fromBinaryRepresentation("C200B40A82")
        assertEquals(3, packet.value())

    }
}
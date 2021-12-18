package io.victoor.aco2021.model.binaryTransmission

data class LiteralDataPacket(val binaryRepresentation: String) : DataPacket(binaryRepresentation) {
    private val parts = content.windowed(5, 5)
    private val length: Int
    val intValue: Long

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
        intValue = binaryValue.toLong( 2)
        this.length = length + header.length
    }

    override fun versionSum(): Int = header.version
    override fun value(): Long = intValue
    override fun getLength(): Int = length


}
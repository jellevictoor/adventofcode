package io.victoor.aco2021.model.binaryTransmission

data class DataHeader(val version: Int, val type: Int) {
    val length: Int = 6

    companion object {
        fun from(binaryRepresentation: String): DataHeader {
            return DataHeader(
                Integer.parseInt(binaryRepresentation.substring(0, 3), 2),
                Integer.parseInt(binaryRepresentation.substring(3, 6), 2)
            )
        }
    }
}
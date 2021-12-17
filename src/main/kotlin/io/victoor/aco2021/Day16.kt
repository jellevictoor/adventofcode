package io.victoor.aco2021

class Day16 {}


interface DataPacketValue {
    fun getLength(): Int
    fun getValue(): Int
    fun getVersionSum(): Int
}
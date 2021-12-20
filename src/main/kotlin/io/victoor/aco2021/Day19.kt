package io.victoor.aco2021

data class Point3d(val x: Int, val y: Int, val z: Int)
data class Beacon(val position: Point3d)
data class Scanner(val knownMap: List<Beacon>)
class Matcher(val scanner:List<Scanner>){

}
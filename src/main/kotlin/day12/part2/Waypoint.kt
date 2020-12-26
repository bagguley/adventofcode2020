package day12.part2

class Waypoint(var x:Int, var y:Int) {
    fun rotate(num: Int) {
        val x1 = (x * cos(num) - y * sin(num))
        val y1 = (x * sin(num) + y * cos(num))
        x = x1
        y = y1
    }

    fun cos(num: Int): Int {
        return when (num) {
            90 -> 0
            -90 -> 0
            180 -> -1
            -180 -> -1
            270 -> 0
            -270 -> 0
            else -> throw RuntimeException("Bad angle $num")
        }
    }

    fun sin(num: Int): Int {
        return when (num) {
            90 -> 1
            -90 -> -1
            180 -> 0
            -180 -> 0
            270 -> -1
            -270 -> 1
            else -> throw RuntimeException("Bad angle $num")
        }
    }
}
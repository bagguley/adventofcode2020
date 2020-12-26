package day13.part1

import day13.data

fun main() {
    val timetable = data
    val depart = timetable[0].toInt()
    val busses = timetable[1].split(",").filter{ it != "x"}.map { it.toInt() }

    val x = busses.map{calc(depart, it)}.minByOrNull { it.second }

    val wait = x!!.second - depart
    println(x.first * wait)

}

fun calc(depart: Int, bus: Int): Pair<Int,Int> {
    return bus to if (depart % bus == 0) {
        depart
    } else {
        ((depart / bus) + 1) * bus
    }
}
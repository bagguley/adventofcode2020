package day13.part2

import day13.data

fun main() {
    println("Start")
    val timetable = data
    val busses = timetable[1].split(",").mapIndexed { index, s -> Pair(index,s) }.filter { it.second != "x" }
        .map { Pair(it.first.toLong(), it.second.toLong()) }

    var time: Long = 0
    var step: Long = 1
    for (bus in busses) {
        while((time + bus.first) % (bus.second) != 0L) {
            time += step
        }
        step *= bus.second
    }
    println(time)
}
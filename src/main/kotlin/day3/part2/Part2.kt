package day3.part2

import day3.data

fun main() {
    println(calc2(1,1) * calc2(3,1) * calc2(5,1) * calc2(7,1) * calc2(1,2))
}

fun calc2(step: Int, drop: Int): Long {
    var xidx = 0
    var yidx = 0
    var count = 0L
    val len = data.first().length
    while(yidx < data.size) {
        val row = data[yidx]
        if (row[xidx] == '#') {
            count++
        }
        xidx = (xidx + step) % len
        yidx += drop
    }
    return count
}
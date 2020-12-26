package day3.part1

import day3.data

fun main() {
    var idx = 0
    var count = 0
    val len = data.first().length
    data.forEach {
        if (it[idx] == '#') count++
        idx = (idx+3)%len
    }
    println(count)
}
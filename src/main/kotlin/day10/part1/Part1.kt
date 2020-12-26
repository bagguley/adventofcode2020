package day10.part1

import day10.data

const val joltageDifference = 3

fun main() {
    val adaptors = data.toMutableList()
    adaptors.add(0, 0)
    adaptors.add(adaptors.size, adaptors.last() + joltageDifference)

    println(count(1, adaptors) * count(3, adaptors))
}

fun count(diff: Int, adaptors: List<Int>) :Int {
    var count = 0

    for (i in 0 until (adaptors.size-1)) {
        if (adaptors[i+1] - adaptors[i] == diff) count++
    }
    return count
}
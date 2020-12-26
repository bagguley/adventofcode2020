package day9.part1

import day9.data

fun main() {
    find(25, data)
}

fun find(windowSize: Int, input: List<Long>) {
    val size = input.size

    for (x in windowSize until size) {
        val preamble = input.subList(x-windowSize, x)
        if (!canFind(input[x], preamble)) println(input[x])
    }
}

fun canFind(value: Long, input: List<Long>):Boolean {
    for (y in 0..(input.size-2)) {
        for (z in 1 until input.size) {
            if (input[y] + input[z] == value) return true
        }
    }
    return false
}
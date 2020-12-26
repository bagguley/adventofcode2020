package day1.part1

import day1.data

fun main() {
    val input = data.toMutableList()
    val result = mutableListOf<Pair<Int,Int>>()
    while (input.size > 1) {
        val x = input.removeAt(0)
        input.find { it + x == 2020 }?.let { result.add(x to it) }
    }
    result.forEach { println(it.first * it.second) }
}

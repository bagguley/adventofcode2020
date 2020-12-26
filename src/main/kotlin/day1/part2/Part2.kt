package day1.part2

import day1.data

fun main() {
    val input = data.toMutableList()
    val result = mutableListOf<Int>()
    while (input.size > 2) {
        val x = input.removeAt(0)
        val tail = input.toMutableList()
        while (tail.size > 1) {
            val y = tail.removeAt(0)
            tail.find { it + x + y == 2020 }?.let { result.add(it * x * y) }
        }
    }
    result.forEach { println(it) }
}
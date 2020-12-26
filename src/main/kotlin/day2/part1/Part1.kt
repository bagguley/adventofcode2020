package day2.part1

import day2.data

fun main() {
    val input = data.map{it.split(Regex("(-| |: )"))}
    input.count{
        val min = it[0].toInt()
        val max = it[1].toInt()
        val letter = it[2][0]
        val pass = it[3]
        pass.count{it == letter} in min..max
    }.let { print(it) }
}
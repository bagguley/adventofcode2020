package day2.part2

import day2.data

fun main() {
    val input = data.map{it.split(Regex("(-| |: )"))}
    input.count{
        val min = it[0].toInt() - 1
        val max = it[1].toInt() - 1
        val letter = it[2][0]
        val pass = it[3]
        (pass.getOrElse(min) {' '} == letter) xor (pass.getOrElse(max) { ' ' } == letter)
    }.let { print(it) }
}
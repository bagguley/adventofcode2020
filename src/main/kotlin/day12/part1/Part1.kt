package day12.part1

import day12.data
import kotlin.math.abs

fun main() {
    val instructions = loadInstructions(data)

    val boat = Boat()

    for (ins in instructions) {
        ins.apply(boat)
    }

    println(abs(boat.position.x) + abs(boat.position.y))
}

fun loadInstructions(instr: List<String>): List<Operation> {
    val result = mutableListOf<Operation>()
    instr.forEach {
        when(it[0]) {
            'N' -> {
                val num = it.substring(1).toInt()
                North(num)
            }
            'S' -> {
                val num = it.substring(1).toInt()
                South(num)
            }
            'E' -> {
                val num = it.substring(1).toInt()
                East(num)
            }
            'W' -> {
                val num = it.substring(1).toInt()
                West(num)
            }
            'L' -> {
                val num = it.substring(1).toInt()
                RotateLeft(num)
            }
            'R' -> {
                val num = it.substring(1).toInt()
                RotateRight(num)
            }
            'F' -> {
                val num = it.substring(1).toInt()
                Forward(num)
            }
            else -> {
                throw Exception("Bad input.")
            }
        }.let { r -> result.add(r) }
    }
    return result
}

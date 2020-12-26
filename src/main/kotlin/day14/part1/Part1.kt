package day14.part1

import day14.data
import java.lang.RuntimeException

fun main() {
    val instr = load(data)
    val computer = Computer()
    instr.forEach { it.apply(computer) }
    println(computer.sum())
}

fun load(input: List<String>): List<Operation> {
    return input.map { load(it) }
}

fun load(input: String): Operation {
    if (input.startsWith("mask = ")) {
        val value = input.substring(7)
        return MaskOp(value.reversed())
    } else if (input.startsWith("mem")) {
        val mem = input.substring(3).split(" = ")
        val address = mem[0].substring(1, mem[0].length-1).toInt()
        val value = mem[1].toInt()
        return MemOp(address, value)
    } else {
        throw RuntimeException("Bad instruction: $input")
    }
}
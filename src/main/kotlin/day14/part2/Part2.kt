package day14.part2

import day14.data
import java.lang.RuntimeException
import kotlin.math.pow

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
    return when {
        input.startsWith("mask = ") -> MaskOp(input.substring(7).reversed())
        input.startsWith("mem") -> {
            val mem = input.substring(3).split(" = ")
            val address = mem[0].substring(1, mem[0].length-1).toInt()
            val value = mem[1].toInt()
            MemOp(address, value)
        }
        else -> throw RuntimeException("Bad instruction: $input")
    }
}

fun CharArray.parseLong(): Long {
    var value: Long = 0
    for (i in this.indices) {
        when (this[i]) {
            ('1') -> value += 2.0.pow(i).toLong()
        }
    }
    return value
}

val testData2 = """mask = 000000000000000000000000000000X1001X
mem[42] = 100
mask = 00000000000000000000000000000000X0XX
mem[26] = 1""".split("\n")
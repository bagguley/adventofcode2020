package day14.part2

import kotlin.math.pow

class Computer {
    private val memory = mutableMapOf<Long, Memory>()
    private var mask = "X".repeat(36)

    fun setMask(mask: String) {
        this.mask = mask
    }

    fun sum(): Long = memory.map { it.value.read() }.sum()

    fun get(address: Long): Memory = memory.getOrPut(address){ Memory() }

    fun getMask(): String = mask
}



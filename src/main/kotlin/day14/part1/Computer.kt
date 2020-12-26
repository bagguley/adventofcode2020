package day14.part1

import kotlin.math.pow

class Computer {
    private val memory = mutableMapOf<Int, Memory>()
    private var mask = "X".repeat(36)

    fun setMask(mask: String) {
        this.mask = mask
    }

    fun sum(): Long {
        return memory.map { Long.parseLong(String(it.value.read())) }.sum()
    }

    fun get(address: Int): Memory {
        return memory.getOrPut(address){Memory(CharArray(36){'0'})}
    }

    fun getMask(): String {
        return mask
    }
}

private fun Long.Companion.parseLong(string: String) : Long {
    var value: Long = 0
    for (i in string.indices) {
        when (string[i]) {
            ('1') -> value += 2.0.pow(i).toLong()
        }
    }
    return value
}

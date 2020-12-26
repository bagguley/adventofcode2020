package day8.part1

import day8.data

fun main() {
    val history = mutableSetOf<Int>()
    var pc = 0
    var acc = 0

    while(!history.contains(pc)) {
        history.add(pc)
        val dat = data[pc].substring(4).toInt()
        when(data[pc].take(3)) {
            "acc" -> {acc += dat; pc +=1}
            "jmp" -> pc += dat
            "nop" -> pc += 1
        }
    }

    println(acc)
}
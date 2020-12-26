package day8.part2

import day8.data

fun main() {
    val errs = data.mapIndexed { index, s -> index to s.take(3)}
        .filter { it.second == "jmp" || it.second == "nop" }.map { it.first }

    for (i in errs) {
        val res = doRun(i, data)
        if (!res.first) {
            println(res.second)
            break
        }
    }
}

fun doRun(error: Int, list: List<String>): Pair<Boolean, Int> {
    val history = mutableSetOf<Int>()
    var pc = 0
    var acc = 0
    while(!history.contains(pc) && pc < list.size) {
        history.add(pc)
        val dat = list[pc].substring(4).toInt()
        when(list[pc].take(3)) {
            "acc" -> {acc += dat; pc +=1}
            "jmp" -> pc += when (pc==error) {true -> 1; false -> dat}
            "nop" -> pc += when (pc==error) {true -> dat; false -> 1}
        }
    }
    return history.contains(pc) to acc
}
package day9.part2

import day9.data

fun main() {
    val toFind = 41682220L
    contig(toFind, data)
}

fun contig(target: Long, input: List<Long>) {
    for (x in 0..input.size-2) {
        if (findFrom(x, target, input)) return
    }
}

fun findFrom(from: Int, target: Long, input: List<Long>):Boolean {
    var sum = input[from]

    for (x in (from+1) until input.size) {
        sum += input[x]
        if (sum == target) {
            val min = (input.subList(from, x+1).minOrNull())
            val max = (input.subList(from, x+1).maxOrNull())
            println(min!! + max!!)
            return true
        }
    }
    return false
}
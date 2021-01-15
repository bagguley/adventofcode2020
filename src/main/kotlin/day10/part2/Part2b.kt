package day10.part2

import day10.data
import java.lang.Long.min

fun main() {
    val adaptors = data.toMutableList().let{it.add(0,0);it.add(it.size,it.last()+joltageDifference);it}

    val t1 = System.currentTimeMillis()
    val loadedWays = process2b(adaptors)
    val t2 = System.currentTimeMillis()
    println(t2 - t1)
    println(loadedWays.last())

    var loadedWays2: Array<Long> = Array(0) { 0L }
    var x1 = 9999999L
    for (i in 1..1000) {
        val t3 = System.currentTimeMillis()
        for (j in 1..1_000_000) {
            loadedWays2 = process2b(adaptors)
        }
        val t4 = System.currentTimeMillis()
        x1 = min(x1, t4-t3)
        println(x1)
        println(x1 / 1_000_000.0)
    }
    println(loadedWays2.last())
}

fun process2b(items: List<Int>): Array<Long> {
    val result = Array(items.size) { 0L }
    result[0] = 1L
    val rev = items.reversed()

    rev.forEachIndexed { index, it ->
        for (a in index-1 downTo 0) if (rev[a] - it in 1..joltageDifference) result[index] += (result[a]) else break}

    return result
}

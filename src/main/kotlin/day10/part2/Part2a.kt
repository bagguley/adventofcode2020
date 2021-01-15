package day10.part2

import day10.data
import java.lang.Long.max

fun main() {
    val adaptors = data.toMutableList().let{it.add(0,0);it.add(it.size,it.last()+joltageDifference);it}

    val t1 = System.currentTimeMillis()
    val loadedWays = process2a(adaptors)
    val t2 = System.currentTimeMillis()
    println(t2 - t1)
    println(loadedWays.last().possibilities)

    val t3 = System.currentTimeMillis()
    var loadedWays2: List<Way2> = mutableListOf()
    for (i in 1..100_000 ) {
        loadedWays2 = process2a(adaptors)
    }
    val t4 = System.currentTimeMillis()
    println(t4-t1)
    println((t4-t3)/100_000.0)
    println(loadedWays2.last().possibilities)
}

fun process2a(items: List<Int>): List<Way2> {
    val wayMap : MutableMap<Int, Way2> = mutableMapOf()
    val result:MutableList<Way2> = mutableListOf()
    val rev = items.reversed()

    rev.forEachIndexed { index, it ->
        val way = Way2(it)
        wayMap[it] = way
        result.add(way)

        for (a in index-1 downTo 0) {
            val v = rev[a]
            val diff = v - it
            if (diff in 1..joltageDifference) way.add(wayMap[v]!!)
            if (diff <= 0) break
        }

        way.possibilities = max(1, way.next.sumOf { it.possibilities })
    }

    return result
}

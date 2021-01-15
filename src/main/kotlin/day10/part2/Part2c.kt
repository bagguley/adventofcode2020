package day10.part2

import day10.data
import java.lang.Long.min

fun main() {
    val adaptors = data.toMutableList().let{it.add(0,0);it.add(it.size,it.last()+3);it}

    val t1 = System.currentTimeMillis()
    val loadedWays = process2c(adaptors)
    val t2 = System.currentTimeMillis()
    println(t2 - t1)
    println(loadedWays.last())

    var loadedWays2: Array<Long> = Array(0) { 0L }
    var x1 = 9999999L
    for (i in 1..1000) {
        val t3 = System.currentTimeMillis()
        for (j in 1..1_000_000) {
            loadedWays2 = process2c(adaptors)
        }
        val t4 = System.currentTimeMillis()
        x1 = min(x1, t4-t3)
        println(x1)
        println(x1 / 1_000_000.0)
    }
    println(loadedWays2.last())
}

fun process2c(items: List<Int>): Array<Long> {
    return items.reversed().let{rev->val res=Array(items.size){0L}; res[0]=1; rev.forEachIndexed{idx,it ->
        for (a in idx-1 downTo 0) if(rev[a]-it in 1..3) res[idx]+=(res[a]) else break}; res}
}

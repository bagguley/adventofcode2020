package day15.part2

import day15.data

fun main() {
    val input = data.split(",").map{it.toInt()}
    val numMap = mutableMapOf<Int, NumberSpoken>()
    input.forEachIndexed() { idx, it -> numMap.getOrPut(it){NumberSpoken(it, idx)} }

    var lastSpoken = numMap.maxByOrNull { it.value.index1 }!!.value
    val startSize = input.size
    val numberSpokenIndex = 30000000
    for (i in startSize until numberSpokenIndex) {
        val num = if (lastSpoken.spokenBefore()) lastSpoken.indexDifference() else 0
        lastSpoken = numMap.getOrPut(num){NumberSpoken(num, i)}.addIndex(i)
    }
    println(lastSpoken.number)
}

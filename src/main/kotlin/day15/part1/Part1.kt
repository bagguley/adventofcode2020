package day15.part1

import day15.data

fun main() {
    println("Start")
    val input = data.split(",").map{it.toInt()}

    val numMap = mutableMapOf<Int, NumberSpoken>()
    var lastSpoken = NumberSpoken(-1)
    input.forEachIndexed() { idx, it ->
        lastSpoken = numMap.getOrPut(it){NumberSpoken(it)}
        lastSpoken.addIndex(idx)
        lastSpoken.incrementTimesSpoken()
    }

    val startSize = input.size
    val numberSpokenIndex = 2020
    for (i in startSize until numberSpokenIndex) {
        val num = if (lastSpoken.timesSpoken == 1) {
            0
        } else {
            lastSpoken.indexDifference()
        }

        lastSpoken = numMap.getOrPut(num){NumberSpoken(num)}
        lastSpoken.addIndex(i)
        lastSpoken.incrementTimesSpoken()
    }
    println(lastSpoken.number)

}
package day22.part1

import day22.data
import day22.testData

fun main() {
    val input = data

    val player1 = input[0].split("\n").drop(1).map{it.toInt()}
    val player2 = input[1].split("\n").drop(1).map{it.toInt()}

    val p1 = player1.toMutableList()
    val p2 = player2.toMutableList()

    println(p1)
    println(p2)

    while (p1.isNotEmpty() && p2.isNotEmpty()) {
        val c1 = p1.removeFirst()
        val c2 = p2.removeFirst()

        if (c1 > c2) {
            p1.add(c1)
            p1.add(c2)
        } else {
            p2.add(c2)
            p2.add(c1)
        }
    }

    val winner = if (p1.isEmpty()) p2 else p1

    var x = winner.size
    var score = 0L
    for (w in winner) {
        score += x * w
        x--
    }

    println(score)
}
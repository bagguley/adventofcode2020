package day11.part1

import day11.data

fun main() {
    var seats = data.map { it.toCharArray() }

    while (true) {
        val x = process(seats)
        if (compare(x, seats)) break
        seats = x
    }
    val z = seats.map { it.count { it == '#' } }.sum()
    println(z)
}

fun process(seats: List<CharArray>): List<CharArray> {
    val width = seats.first().size
    val height = seats.size
    val result = mutableListOf<CharArray>()
    seats.forEach { result.add(CharArray(width){'.'}) }
    for (h in 0 until height) {
        for (w in 0 until width) {
            result[h][w] = newState(seats, w, h)
        }
    }
    return result
}

fun newState(seats: List<CharArray>, x: Int, y: Int): Char {
    val width = seats.first().size
    val height = seats.size
    val d = arrayListOf(x-1, y-1, x, y-1, x+1, y-1, x-1, y, x+1, y, x-1, y+1, x, y+1, x+1, y+1).windowed(2, 2)
        .map { (first, second) -> Pair(first, second)}.filter{ it.first in 0 until width && it.second in 0 until height }

    return when (val seat = seats[y][x]) {
        'L' -> if (d.map { seats[it.second][it.first] }.filter{it=='#'}.count() == 0) '#' else seat
        '#' -> if (d.map { seats[it.second][it.first] }.filter{it=='#'}.count() >= 4) 'L' else seat
        else -> seat
    }
}

fun compare(seats1: List<CharArray>, seats2: List<CharArray>): Boolean {
    return seats1.zip(seats2).all{ it.first.contentEquals(it.second) }
}
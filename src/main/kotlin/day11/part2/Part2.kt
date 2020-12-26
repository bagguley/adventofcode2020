package day11.part2

import day11.data

fun main() {
    var seats = data.map { it.toCharArray() }

    while (true) {
        val x = process2(seats)
        if (compare2(x, seats)) break
        seats = x
    }
    val z = seats.map { it.count { it == '#' } }.sum()
    println(z)
}

fun process2(seats: List<CharArray>): List<CharArray> {
    val width = seats.first().size
    val height = seats.size
    val result = mutableListOf<CharArray>()
    seats.forEach { result.add(CharArray(width){'.'}) }
    for (y in 0 until height) {
        for (x in 0 until width) {
            result[y][x] = newState2(seats, x, y)
        }
    }
    return result
}

fun newState2(seats: List<CharArray>, x: Int, y: Int): Char {
    val width = seats.first().size
    val height = seats.size
    val d = squaresToCheck2(seats, x, y, width, height)

    return when (val seat = seats[y][x]) {
        'L' -> if (d == 0) '#' else seat
        '#' -> if (d >= 5) 'L' else seat
        else -> seat
    }
}

fun compare2(seats1: List<CharArray>, seats2: List<CharArray>): Boolean {
    return seats1.zip(seats2).all{ it.first.contentEquals(it.second) }
}

fun squaresToCheck2(seats: List<CharArray>, startx: Int, starty: Int, width:Int, height: Int): Int{
    var result = 0
    result += first(seats, line(startx, starty, width, height, -1, -1))
    result += first(seats, line(startx, starty, width, height, -1, 0))
    result += first(seats, line(startx, starty, width, height, -1, 1))
    result += first(seats, line(startx, starty, width, height, 0, -1))
    result += first(seats, line(startx, starty, width, height, 0, 1))
    result += first(seats, line(startx, starty, width, height, 1, -1))
    result += first(seats, line(startx, starty, width, height, 1, 0))
    result += first(seats, line(startx, starty, width, height, 1, 1))

    return result
}

fun first(seats: List<CharArray>, sight: List<Pair<Int,Int>>): Int {
    return if (sight.map{seats[it.second][it.first]}.find{it != '.'} == '#') 1 else 0
}

fun line(startx: Int, starty: Int, width:Int, height: Int, dx: Int, dy:Int): List<Pair<Int,Int>> {
    val result = mutableListOf<Pair<Int,Int>>()
    var x = startx + dx
    var y = starty + dy
    while (x in 0 until width && y in 0 until height) {
        result.add(Pair(x, y))
        x += dx
        y += dy
    }
    return result
}
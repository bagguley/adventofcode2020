package day24.part2

import day24.data
import day24.testData

var x: Int = 0
var y: Int = 0

var hexMap = mutableMapOf<String, String>()
var nextMap = mutableMapOf<String, String>()

fun main() {
    load(data)
}

fun load(input: List<String>) {
    for (line in input) {
        x = 0
        y = 0
        val t = Regex("(ne|e|se|sw|w|nw)").findAll(line).toList().map{it.value}
        for (x in t) {
            when (x) {
                "ne" -> northEast()
                "e" -> east()
                "se" -> southEast()
                "sw" -> southWest()
                "w" -> west()
                "nw" -> northWest()
            }
        }
        val h = hexMap.getOrPut("$x,$y"){"w"}
        when (h) {
            "w" -> hexMap.put("$x,$y", "b")
            "b" -> hexMap.put("$x,$y", "w")
        }
    }
    val r = hexMap.values.count { it == "b" }

    println(r) // Part 1 answer

    for (i in 1..100) {
        nextMap = hexMap.toMutableMap()
        val keys = hexMap.keys
        for (k in keys) {
            val v = hexMap[k]!!
            val x = k.split(",")[0].toInt()
            val y = k.split(",")[1].toInt()

            mutate(v, x, y)
        }

        val allEdges = allEdges()
        for (e in allEdges) {
            val x = e.split(",")[0].toInt()
            val y = e.split(",")[1].toInt()
            mutate("w", x, y)
        }
        hexMap = nextMap
    }

    val r2 = hexMap.values.count { it == "b" }

    println(r2) // Part 2 answer
}

fun mutate(v: String, x: Int, y: Int) {
    val c = countBlackNeighbours(x, y)

    when (v) {
        "b" -> {
            if (c == 0 || c > 2) {
                nextMap["$x,$y"] = "w"
            }
        }
        "w" -> {
            if (c == 2) {
                nextMap["$x,$y"] = "b"
            }
        }
    }
}

fun countBlackNeighbours(x: Int, y: Int): Int {
    var count = 0

    if (isBlack(x+1, y-1)) count++
    if (isBlack(x+2, y)) count++
    if (isBlack(x+1, y+1)) count++
    if (isBlack(x-1, y+1)) count++
    if (isBlack(x-2, y)) count++
    if (isBlack(x-1, y-1)) count++

    return count
}

fun allEdges(): Set<String> {
    val result = mutableSetOf<String>()
    for (k in hexMap.keys) {
        val x = k.split(",")[0].toInt()
        val y = k.split(",")[1].toInt()

        if (!containsKey(x+1, y-1)) result.add("${x+1},${y-1}")
        if (!containsKey(x+2, y)) result.add("${x+2},$y")
        if (!containsKey(x+1, y+1)) result.add("${x+1},${y+1}")
        if (!containsKey(x-1, y+1)) result.add("${x-1},${y+1}")
        if (!containsKey(x-2, y)) result.add("${x-2},$y")
        if (!containsKey(x-1, y-1)) result.add("${x-1},${y-1}")
    }
    return result
}

fun isBlack(x: Int, y: Int) = hexMap.getOrElse("$x,$y"){"w"} == "b"

fun containsKey(x: Int, y: Int) = hexMap.containsKey("$x,$y")

fun northEast() {
    x += 1
    y -= 1
}

fun east() {
    x += 2
}

fun southEast() {
    x += 1
    y += 1
}

fun southWest() {
    x -= 1
    y += 1
}

fun west() {
    x -= 2
}

fun northWest() {
    x -= 1
    y -= 1
}
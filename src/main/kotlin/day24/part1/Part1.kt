package day24.part1

import day24.data
import day24.testData

var x: Int = 0
var y: Int = 0

val hexMap = mutableMapOf<String, String>()

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
    println(r)
}

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
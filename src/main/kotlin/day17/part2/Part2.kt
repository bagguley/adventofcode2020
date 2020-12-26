package day17.part2

import day17.data

const val active = '#'

var worldMap:MutableMap<String, Boolean> = mutableMapOf()

fun main() {
    val input = data

    initWorld(input)

    advanceWorld()
    advanceWorld()
    advanceWorld()
    advanceWorld()
    advanceWorld()
    advanceWorld()

    println(worldMap.values.filter { it }.count())
}

fun initWorld(input: List<String>) {
    var y = 0
    val z = 0
    val w = 0

    for(line in input) {
        var x = 0
        for (c in line) {
            worldMap["$w,$x,$y,$z"] = (c == active)
            x++
        }
        y++
    }
}

fun advanceWorld() {
    val newWorldMap: MutableMap<String, Boolean> = mutableMapOf()
    val newCells = advanceExisting(newWorldMap)
    advanceEdges(newWorldMap, newCells)
    worldMap = newWorldMap
}

fun advanceExisting(newWorldMap: MutableMap<String, Boolean>): MutableSet<String> {
    val newCells: MutableSet<String> = mutableSetOf()
    for (worldEntry in worldMap) {
        if (worldEntry.value) newWorldMap[worldEntry.key] = (neighbourCount(worldEntry.key, newCells) in 2..3)
        else newWorldMap[worldEntry.key] = (neighbourCount(worldEntry.key, newCells) == 3)
    }
    return newCells
}

fun advanceEdges(newWorldMap: MutableMap<String, Boolean>, newCells: MutableSet<String>) {
    for (newCell in newCells) {
        if (neighbourCount(newCell) == 3) newWorldMap[newCell] = true
    }
}

fun neighbourCount(input: String, newCells: MutableSet<String>): Int {
    val coords = input.split(",").map{it.toInt()}
    return neighbourCount(coords[0], coords[1], coords[2], coords[3], newCells)
}

fun neighbourCount(w: Int, x: Int, y: Int, z: Int, newCells: MutableSet<String>): Int {
    var count = 0
    for (ww in w-1..w+1) {
        for (xx in x - 1..x + 1) {
            for (yy in y - 1..y + 1)
                for (zz in z - 1..z + 1) {
                    if (ww == w && xx == x && yy == y && zz == z) continue
                    val cell: Boolean? = worldMap["$ww,$xx,$yy,$zz"]
                    if (cell != null && cell == true) count++
                    else newCells.add("$ww,$xx,$yy,$zz")
                }
        }
    }
    return count
}

fun neighbourCount(input: String): Int {
    val coords = input.split(",").map{it.toInt()}
    return neighbourCount(coords[0], coords[1], coords[2], coords[3])
}

fun neighbourCount(w: Int, x: Int, y: Int, z: Int): Int {
    var count = 0
    for (ww in w-1..w+1) {
        for (xx in x - 1..x + 1) {
            for (yy in y - 1..y + 1)
                for (zz in z - 1..z + 1) {
                    if (ww == w && xx == x && yy == y && zz == z) continue
                    val cell = worldMap.getOrDefault("$ww,$xx,$yy,$zz", false)
                    if (cell) count++
                }
        }
    }
    return count
}
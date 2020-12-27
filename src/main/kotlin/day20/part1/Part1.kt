package day20.part1

import day20.data

fun main() {
    val tiles = load(data)

    val corners = findCorners(tiles)

    corners.forEach { println(it.number) }
    println(corners.map { it.number }.fold(1L, {a,i -> a * i} ))
}

fun load(data: List<String>): List<Tile> {
    return data.map { loadTile(it) }
}

fun loadTile(data: String): Tile {
    val lines = data.split("\n").filter { it.isNotEmpty() }.toMutableList()

    val first = lines.removeFirst()
    val tileNum = first.substring(5, 9).toInt()

    return Tile(tileNum, lines)
}

fun findCorners(tiles: List<Tile>): List<Tile> {
    val result:MutableList<Tile> = mutableListOf()
    for (tile in tiles) {
        val otherTiles = tiles.filter { it != tile }
        val otherEdges = otherTiles.flatMap { it.edges() }
        val edges = tile.edges()
        val matchingEdges = edges.filterNot { otherEdges.contains(it) || otherEdges.contains(it.reversed()) }
        if (matchingEdges.size == 2) {
            result.add(tile)
        }
    }
    return result
}

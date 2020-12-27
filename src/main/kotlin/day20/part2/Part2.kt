package day20.part2

import day20.*
import java.lang.StringBuilder

fun main() {
    val tiles = load(data)

    val corners = findCorners(tiles)

    println(corners.map { it.number }.fold(1L, {a,i -> a * i} ))

    val edges = findEdges(tiles)

    val allEdges: MutableList<Tile> = mutableListOf()
    allEdges.addAll(corners)
    allEdges.addAll(edges)

    val tileMap: MutableMap<String,Tile> = mutableMapOf()

    val first = corners.first()
    allEdges.remove(first)
    tileMap["0,0"] = first

    // Rotate 0,0 to be correct
    val topLeft = tileMap["0,0"]!!

    while(true) {
        val cornerEdges = listOf(topLeft.left(), topLeft.top())
        if (!matches(cornerEdges, allEdges)) break
        topLeft.rotate()
    }

    // Fill top row
    println("Fill top row")
    for (x in 1..11) {
        val leftTile = tileMap["${x-1},0"]!!
        val leftTileRightEdge = leftTile.right()
        val nextTile = allEdges.find { it.matches(leftTileRightEdge) }!!

        // Rotate it
        while(true) {
            val nextTileLeftEdge = nextTile.left()
            if (leftTileRightEdge == nextTileLeftEdge) break
            if (leftTileRightEdge == nextTileLeftEdge.reversed()) {
                nextTile.flip()
                break
            }
            nextTile.rotate()
        }

        tileMap["$x,0"] = nextTile
        allEdges.remove(nextTile)
    }

    // Fill remaining rows
    println("Fill remaining rows")
    val allTiles: MutableList<Tile> = tiles.toMutableList()
    allTiles.removeAll(tileMap.values)
    for (y in 1..11) {
        for (x in 0..11) {
            val aboveTile = tileMap["$x,${y - 1}"]!!
            val aboveTileBottomEdge = aboveTile.bottom()
            val nextTile = allTiles.find { aboveTile.matches(it) }!!

            // Rotate it
            while (true) {
                val nextTileTopEdge = nextTile.top()
                if (aboveTileBottomEdge == nextTileTopEdge) break
                if (aboveTileBottomEdge == nextTileTopEdge.reversed()) {
                    nextTile.flip()
                }
                nextTile.rotate()
            }

            tileMap["$x,$y"] = nextTile
            allTiles.remove(nextTile)
        }
    }

    // Remove outer edges
    println("Remove outer edges")
    tileMap.values.forEach{it.removeEdges()}

    // Build picture
    println("Build picture")
    var picture = createPicture(tileMap)

    // Find monsters
    println("Find monsters")
    var c1 = 0
    var r = 1
    while (c1 == 0) {
        picture = if (r % 4 == 0) flip(picture) else rotate(picture)
        c1 = countMonsters(picture)
        r++
    }

    val uniqueMonsterSquares = countMonstersSquares(picture).size
    val countOfHashes = picture.sumBy { it.count { it == '#' } }

    println("Monsters: $c1")
    println("Total #'s: $countOfHashes")
    println("Monster #: $uniqueMonsterSquares")
    println("Not affected #: " + (countOfHashes - uniqueMonsterSquares))
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

fun findEdges(tiles: List<Tile>): List<Tile> {
    val result:MutableList<Tile> = mutableListOf()
    for (tile in tiles) {
        val otherTiles = tiles.filter { it != tile }
        val otherEdges = otherTiles.flatMap { it.edges() }
        val edges = tile.edges()
        val matchingEdges = edges.filterNot { otherEdges.contains(it) || otherEdges.contains(it.reversed()) }
        if (matchingEdges.size == 1) {
            result.add(tile)
        }
    }
    return result
}

fun matches(edges: List<String>, tile: List<Tile>): Boolean {
    val otherEdges = tile.flatMap { it.edges() }
    val matchingEdges = edges.filter { otherEdges.contains(it) }
    return matchingEdges.isNotEmpty()
}

fun createPicture(tileMap: MutableMap<String, Tile>): List<String> {
    val picture: MutableList<String> = mutableListOf()
    for (y in 0..11) {
        for (yy in 0..7) {
            val sb = StringBuilder()
            for (x in 0..11) {
                sb.append(tileMap["$x,$y"]!!.row(yy))
            }
            picture.add(sb.toString())
        }
    }
    return picture
}

fun countMonsters(picture: List<String>): Int {
    var count = 0
    val pictureWidth = picture[0].length
    val pictureHeight = picture.size
    for (y in 0 until pictureHeight - monsterHeight) {
        for (x in 0 until pictureWidth - monsterWidth) {
            val section = grabSection(picture, x, y)
            if (checkMonster(section)) count++
        }
    }
    return count
}

fun countMonstersSquares(picture: List<String>): Set<String> {
    val squareSet: MutableSet<String> = mutableSetOf()
    var count = 0L
    val pictureWidth = picture[0].length
    val pictureHeight = picture.size
    for (y in 0 until pictureHeight - monsterHeight) {
        for (x in 0 until pictureWidth - monsterWidth) {
            val section = grabSection(picture, x, y)
            if (checkMonster(section)) {
                count++
                squareSet.addAll(monsterCoords.map { "${it.first+x},${it.second+y}" })
            }
        }
    }
    return squareSet
}

fun grabSection(picture: List<String>, x: Int, y: Int): List<String> {
    val section: MutableList<String> = mutableListOf()

    for (yy in y until y + monsterHeight) {
        section.add(picture[yy].substring(x, x + monsterWidth))
    }

    return section
}

fun checkMonster(section: List<String>): Boolean {
    return monsterCoords.all { section[it.second][it.first] == '#' }
}

fun rotate(picture: List<String>): List<String> {
    val newPicture = picture.toMutableList()
    for (x in 0 until 96) {
        val sb = StringBuilder()
        for (y in 95 downTo 0) {
            sb.append(picture[y][x])
        }
        newPicture[x] = sb.toString()
    }
    return newPicture
}

fun flip(picture: List<String>): List<String> {
    return picture.reversed()
}
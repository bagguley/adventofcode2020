package day20.part2

import java.lang.StringBuilder

class Tile(val number: Int, var data: List<String>) {
    fun edges(): List<String> {
        return listOf(top(), right(), bottom(), left())
    }

    fun top(): String {
        return data.first()
    }

    fun right(): String {
        return String(data.map{it.last()}.toCharArray())
    }

    fun bottom(): String {
        return data.last()
    }

    fun left(): String {
        return String(data.map{it.first()}.toCharArray())
    }

    fun trim() {
        val newData:MutableList<String> = mutableListOf()
        val middle = data.subList(1, data.size-1)
        for (m in middle) {
            newData.add(m.substring(1, 11))
        }
    }

    fun matches(tile: Tile): Boolean {
        val edges = edges()
        val otherEdges = tile.edges()
        return edges.any { otherEdges.contains(it) || otherEdges.contains(it.reversed()) }
    }

    fun matches(edge: String): Boolean {
        return edges().any { it == edge || it == edge.reversed() }
    }

    fun rotate() {
        val newData = data.toMutableList()
        for (x in 0..9) {
            val sb = StringBuilder()
            for (y in 9 downTo 0) {
                sb.append(data[y][x])
            }
            newData[x] = sb.toString()
        }
        data = newData
    }

    fun flip() {
        data = data.reversed()
    }

    fun removeEdges() {
        val newData: MutableList<String> = mutableListOf()
        for (y in 1..8) {
            newData.add(data[y].drop(1).dropLast(1))
        }
        data = newData
    }

    fun row(index: Int): String {
        return data[index]
    }
}
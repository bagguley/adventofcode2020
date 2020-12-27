package day20.part1

class Tile(val number: Int, val data: List<String>) {
    fun edges(): List<String> {
        return listOf(data.first(), data.last(), String(data.map{it.first()}.toCharArray()),
            String(data.map{it.last()}.toCharArray()))
    }
}
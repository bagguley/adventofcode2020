package day10.part2

import day10.data

const val joltageDifference = 3

fun main() {
    val useData = data
    val biggest = useData.last() + joltageDifference
    val adaptors = useData.toMutableList()
    adaptors.add(0, 0)
    adaptors.add(biggest)

    val loadedWays = load(adaptors.sorted())

    for (w in (loadedWays.size-2) downTo 0)
    {
        var count = 0L
        val way = loadedWays[w]
        for (num in way.next) {
            val child = findWay(num, loadedWays)
            count += child.possibilities
        }
        way.possibilities = count
    }
    println(loadedWays.first().possibilities)
}

fun load(items: List<Int>): List<Way> {
    val result:MutableList<Way> = mutableListOf()
    for (i in items) {
        val next:MutableList<Int> = mutableListOf()
        for (a in items) {
            val diff = a - i
            if (diff in 1..joltageDifference) next.add(a)
            if (diff > joltageDifference) break
        }
        result.add(Way(i, next))
    }
    return result
}

fun findWay(num: Int, ways: List<Way>): Way {
    for (way in ways) {
        if (way.number == num) return way
    }
    throw RuntimeException("Error")
}
package day15.part1

class NumberSpoken(val number: Int) {
    val indexes = mutableListOf<Int>()
    var timesSpoken: Int = 0

    fun addIndex(index: Int) {
        indexes.add(index)
    }

    fun incrementTimesSpoken() {
        timesSpoken++
    }

    fun indexDifference(): Int {
        return indexes.takeLast(2).windowed(2, 1).map{ it[1] - it[0]}.first()
    }
}
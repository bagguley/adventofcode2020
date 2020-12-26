package day15.part2

class NumberSpoken(val number: Int, index: Int) {
    var index1: Int = index
    var index2: Int = index

    fun addIndex(index: Int): NumberSpoken {
        index1 = index2
        index2 = index
        return this
    }

    fun indexDifference(): Int {
        return index2 - index1
    }

    fun spokenBefore(): Boolean {
        return index1 != index2
    }
}
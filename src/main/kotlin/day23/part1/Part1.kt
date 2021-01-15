package day23.part1

var cups = mutableListOf<Int>()
var currentCupNumber = 0
var cupsSize = 0

fun main() {
    cups = "389547612".map { it.toString().toInt() }.toMutableList()
    cupsSize = cups.size
    currentCupNumber = cups.first()

    for (i in 1..100) {
        action()
    }

    val ans = (cups.dropWhile { it != 1 }.drop(1) + cups.takeWhile { it != 1 }).fold(""){a,i->a+i.toString()}
    println(ans)
}

fun action() {
    val taken = take3()
    cups.removeAll(taken)

    addAfter(taken)
    val currentCupIndex = cups.indexOf(currentCupNumber)
    currentCupNumber = cups[(currentCupIndex + 1) % cupsSize]
}

fun take3(): List<Int> {
    val currentCupIndex = cups.indexOf(currentCupNumber)
    // Take 3
    val taken = mutableListOf<Int>()
    for (i in 1..3) {
        taken.add(cups[(currentCupIndex + i) % cupsSize])
    }
    return taken
}

fun addAfter(taken: List<Int>) {
    var nextCupNumber = currentCupNumber - 1
    if (nextCupNumber == 0) nextCupNumber = cupsSize

    var nextCupIndex = -1
    while (nextCupIndex == -1) {
        nextCupIndex = cups.indexOf(nextCupNumber)
        if (nextCupIndex == -1) nextCupNumber -= 1
        if (nextCupNumber == 0) nextCupNumber = cupsSize
    }

    for (i in 1..3) {
        val addIndex = (nextCupIndex + i) % cupsSize
        cups.add(addIndex, taken[i-1])
    }
}
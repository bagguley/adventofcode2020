package day16.part2

import java.lang.RuntimeException

class Rule(private val name: String, private val range1: IntRange, private val range2: IntRange) {
    fun isInRange(toCheck: Int): Boolean {
        return toCheck in range1 || toCheck in range2
    }

    fun isDeparture(): Boolean {
        return name.startsWith("departure")
    }

    override fun toString(): String {
        return "$name $range1, $range2"
    }
}

fun parseRule(input: String): Rule {
    val regex = Regex("([a-zA-Z ]*): (\\d*)-(\\d*) or (\\d*)-(\\d*)")
    val match = regex.matchEntire(input) ?: throw RuntimeException("Bad input: $input")

    return Rule(match.groupValues[1], IntRange(match.groupValues[2].toInt(), match.groupValues[3].toInt()),
        IntRange(match.groupValues[4].toInt(), match.groupValues[5].toInt()))
}
package day18.part1

import day18.data

fun main() {
    val result = data.map{calc(it)}.sum()
    println(result)
}

fun calc(string: String): Long {
    var input = string
    while (input.contains("(")) {
        input = parse(input)
    }
    return calcInner(input).toLong()
}

fun parse(string: String): String {

    val regX = Regex("\\((\\d+(?: [+*] \\d+)*)\\)")

    val matchRes = regX.find(string)

    val match = matchRes?.groupValues?.get(1) ?: return string

    val replace = calcInner(match)

    return string.replaceFirst("($match)", replace)
}

fun calcInner(string: String): String {
    val regX = Regex("(\\d+) ([+*]) (\\d+)")

    var str = string

    while(str.contains(Regex("[+*]"))) {
        val matchRes = regX.find(str)

        val matches = matchRes?.groupValues ?: return str

        val first = matches[1].toLong()
        val operator = matches[2]
        val second = matches[3].toLong()

        val result = when (operator) {
            "+" -> first + second
            "*" -> first * second
            else -> throw RuntimeException("Bad operator $operator")
        }

        str = str.replaceFirst(matches[0], result.toString())
    }
    return str
}
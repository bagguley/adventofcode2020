package day18.part2

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
    input = calcAdd(input)
    input = calcMultiply(input)
    return input.toLong()
}

fun parse(string: String): String {
    var str = string
    val regX = Regex("\\((\\d+(?: [+*] \\d+)*)\\)")

    val matchRes = regX.find(str)

    val match = matchRes?.groupValues?.get(1) ?: return str

    str = calcAdd(match)
    str = calcMultiply(str)
    return string.replaceFirst("($match)", str)
}

fun calcAdd(string: String): String {
    val regX = Regex("(\\d+) \\+ (\\d+)")

    var str = string

    while(str.contains(Regex("\\+"))) {
        val matchRes = regX.find(str)

        val matches = matchRes?.groupValues ?: return str

        val first = matches[1].toLong()
        val second = matches[2].toLong()

        val result = first + second

        str = str.replaceFirst(matches[0], result.toString())
    }
    return str
}

fun calcMultiply(string: String) : String {
    val regX = Regex("(\\d+) \\* (\\d+)")

    var str = string

    while (str.contains(Regex("\\*"))) {
        val matchRes = regX.find(str)

        val matches = matchRes?.groupValues ?: return str

        val first = matches[1].toLong()
        val second = matches[2].toLong()

        val result = first * second

        str = str.replaceFirst(matches[0], result.toString())
    }
    return str
}
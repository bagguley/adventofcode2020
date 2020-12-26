package day7.part2

import day7.data

fun main() {
    val bagMap = data.map{it.split(" bags contain ")}.map{ process2(it) }.toMap()

    val stack = mutableListOf(bagMap["shiny gold"])
    var count = 0
    do {
        val c = stack.removeAt(0)
        stack.addAll(c!!.map{f -> bagMap[f.second]!!.map{g -> f.first * g.first to g.second}})
        count += c.map { f -> f.first }.sum()
    } while (stack.isNotEmpty())
    println(count)
}

fun process2(input: List<String>) : Pair<String, List<Pair<Int,String>>> {
    val v = input[1].split(Regex(" bags?[,.]? ?")).filter { it != "no other" && it.isNotEmpty()}
    return input[0] to v.map{ process3(it) }
}

fun process3(input: String) : Pair<Int,String> {
    val x = Regex("(\\d+) (.+)").matchEntire(input)
    return x!!.groupValues[1].toInt() to x.groupValues[2]
}

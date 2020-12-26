package day7.part1

import day7.data

fun main() {
    val a = data.map{it.split(" bags contain ")}
    val bagMap = a.map{ process(it) }

    val bagSet = mutableSetOf<String>()
    val stack = mutableListOf("shiny gold")
    do {
        val str = stack.removeAt(0)
        val c = bagMap.filter{ search(str, it.second) }.map{it.first}
        stack.addAll(c)
        bagSet.addAll(c)
    } while (stack.isNotEmpty())
    println(bagSet.size)
}

fun process(input: List<String>) : Pair<String, List<String>> {
    val x = input[1].split(Regex(" bags?[,.]? ?"))
    return input[0] to x
}

fun search(str: String, lst :List<String>) : Boolean {
    return lst.stream().anyMatch { it.contains(str) }
}
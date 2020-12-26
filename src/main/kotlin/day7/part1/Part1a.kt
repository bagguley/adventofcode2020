package day7.part1

import day7.data

fun main() {
    val bagMap = data.map{it.split(" bags contain ")}.map{ val x = it[1].split(Regex(" bags?[,.]? ?"))
        .filter { it != "no other" && it.isNotEmpty()}; it[0] to x.map{ Regex("(\\d+) (.+)").matchEntire(it)!!.groupValues[2] } }.toMap()
    println(rec(mutableListOf("shiny gold"), bagMap, mutableSetOf()).size)
}

tailrec fun rec(stack: MutableList<String>, bagMap:Map<String, List<String>>, bagSet:MutableSet<String>): MutableSet<String> {
    if (stack.isEmpty()) return bagSet
    stack.removeAt(0).let{str->bagMap.filter{it.value.contains(str)}.map{it.key}.let{stack.addAll(it); bagSet.addAll(it);}}
    return rec(stack, bagMap, bagSet)
}

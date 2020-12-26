package day16.part2

import day16.data

fun main() {
    val input = data

    val rules = input[0].split("\n").map { parseRule(it) }
    val myTicket = input[1].split("\n")[1].split(",").map { it.toInt() }
    val nearBy = input[2].split("\n").drop(1).map{it.split(",").map { it.toInt() }}

    val validTickets = nearBy.filter { isValid(it, rules) }
    val ruleOptions = validTickets.first().map{ rules.toMutableList() }

    validTickets.forEach { it.forEachIndexed{ index, num ->
        ruleOptions[index].removeIf{ rule -> !rule.isInRange(num)}
    } }

    while(ruleOptions.filter { it.size != 1 }.flatten().isNotEmpty()) {
        val single = ruleOptions.filter { it.size == 1 }.flatten()
        ruleOptions.forEach { if (it.size > 1) it.removeAll(single) }
    }

    val departureOptions = ruleOptions.flatten().mapIndexed{index, rule -> Pair(index, rule.isDeparture())}.filter { it.second }

    println(departureOptions.map { myTicket[it.first].toLong() }.reduce { acc, i -> acc * i})
}

fun isValid(list: List<Int>, rules:List<Rule>): Boolean{
    return list.stream().allMatch{ t -> rules.stream().anyMatch { it.isInRange(t) }  }
}
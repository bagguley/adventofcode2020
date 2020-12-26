package day19.part1

import day19.data

val ruleMap: MutableMap<Int, Rule> = mutableMapOf()

fun main() {
    val rules = parseRules(data[0].split("\n"))
    val input = parseInput(data[1].split("\n"))

    for (rule in rules) {
        ruleMap[rule.index] = rule
    }
    val valid = rules[0].validStrings(ruleMap)
    var count = 0
    for (str in input) {
        if (str in valid) count++
    }
    println(count)
}

fun parseInput(input: List<String>): List<String> {
    return input.filter { it.isNotEmpty() }
}

fun parseRules(input: List<String>): List<Rule> {
    return input.filter { it.isNotEmpty() }.map{parseRule(it)}.sortedBy { it.index }
}

fun parseRule(input: String): Rule {
    val split = input.split(": ")
    val index = split[0].toInt()
    val rule = split[1]

    return parseRuleString(index, rule)
}

fun parseRuleString(index: Int, rule: String): Rule {
    return if (rule.contains("\"")) {
        StringRule(index, rule.substring(1, rule.length-1))
    } else {
        if (rule.contains("|")) {
            val leftRule = rule.split(" | ")[0]
            val rightRule = rule.split(" | ")[1]
            OrRule(index, leftRule, rightRule)
        } else {
            PairRule(index, rule)
        }
    }
}
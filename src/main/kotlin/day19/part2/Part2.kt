package day19.part2

import java.util.*

val ruleMap: MutableMap<Int, Rule> = mutableMapOf()

fun main() {
    val rules = parseRules(data2[0].split("\n"))
    val input = parseInput(data2[1].split("\n"))

    for (rule in rules) {
        ruleMap[rule.index] = rule
    }

    val valid = ruleMap[0]!!.validStrings(ruleMap)
    println("Valid size " + valid.size)

    val mInput = input.toMutableList()

    println("input size " + input.size)
    mInput.removeAll(valid)
    println("minput size " + mInput.size)

    val x42 = ruleMap[42]!!.validStrings(ruleMap)

    val regStr42 = StringJoiner("|", "^(", ")+")
    for (a in x42) {
        regStr42.add(a)
    }
    val reg8 = regStr42.toString()

    val x31 = ruleMap[31]!!.validStrings(ruleMap)

    for (i in 1..120) {
        val regstr42 = StringJoiner("|", "(", "){$i}+")
        for (a in x42) {
            regstr42.add(a)
        }

        val regstr31 = StringJoiner("|", "(", "){$i}+")
        for (a in x31) {
            regstr31.add(a)
        }

        val s = "^$reg8$regstr42$regstr31$"
        val r4231 = Regex(s)

        mInput.removeIf { it.matches(r4231) }
    }

    println("minput size " + mInput.size)

    println(input.size - mInput.size)
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

//Valid size 2097152
//input size 475
//minput size 234
//minput size 228
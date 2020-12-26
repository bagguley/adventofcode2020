package day19.part2

val testRuleMap: MutableMap<Int, Rule> = mutableMapOf()

fun main() {
    val rules = """0: 3 | 2
1: "a"
2: "b"
3: 1 2 | 8
8: 42 | 8
42: 1 | 2
11: 42 31 | 42 11 31
31: 2""".split("\n")

    val pRules = parseRules(rules)
    for (rule in pRules) {
        testRuleMap[rule.index] = rule
    }

    println(testRuleMap[11]!!.validStrings(testRuleMap))

    val valid = pRules[0].validStrings(testRuleMap)

    println(valid.contains("bbbb"))
}
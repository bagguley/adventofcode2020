package day19.part1

interface Rule {
    val index: Int
    fun validStrings(ruleMap: Map<Int, Rule>): List<String>
}
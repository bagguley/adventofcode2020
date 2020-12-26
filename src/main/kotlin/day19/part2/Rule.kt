package day19.part2

interface Rule {
    val index: Int
    fun validStrings(ruleMap: Map<Int, Rule>): Set<String>
}
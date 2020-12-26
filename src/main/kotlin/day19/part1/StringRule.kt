package day19.part1

class StringRule(override val index: Int, private val rule: String): Rule {
    override fun validStrings(ruleMap: Map<Int, Rule>) : List<String> {
        return listOf(rule)
    }
}
package day19.part2

class StringRule(override val index: Int, private val rule: String): Rule {
    override fun validStrings(ruleMap: Map<Int, Rule>) : Set<String> {
        return setOf(rule)
    }
}
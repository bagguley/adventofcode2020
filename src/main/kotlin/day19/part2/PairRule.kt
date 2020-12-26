package day19.part2

class PairRule(override val index: Int, private val rules: String) : Rule {
    private var vs: Set<String>? = null
    override fun validStrings(ruleMap: Map<Int, Rule>) : Set<String> {
        if (vs != null) return vs!!
        val splitRules = rules.split(" ").filter{it.isNotEmpty() }.toMutableList()

        var result = mutableSetOf("")
        do {
            val nextResult: MutableSet<String> = mutableSetOf()
            val aString = splitRules.removeAt(0)
            val nextList = ruleMap[aString.toInt()]!!.validStrings(ruleMap)

            for (r in result) {
                for (n in nextList) {
                    nextResult.add(r+n)
                }
            }
            result = nextResult
        } while (splitRules.isNotEmpty())
        vs = result
        return result
    }
}
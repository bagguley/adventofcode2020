package day19.part2

class OrRule(override val index: Int, private val leftPair: String, private val rightPair: String) : Rule {
    private var vs: Set<String>? = null
    override fun validStrings(ruleMap: Map<Int, Rule>) : Set<String> {
        if (vs != null) return vs!!
        val result: MutableSet<String> = mutableSetOf()
        result.addAll(validStrings(ruleMap, leftPair))
        result.addAll(validStrings(ruleMap, rightPair))
        vs = result
        return result
    }

    private fun validStrings(ruleMap: Map<Int, Rule>, rules: String) : Set<String> {
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
        return result
    }
}
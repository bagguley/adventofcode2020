package day19.part1

class PairRule(override val index: Int, private val rules: String) :Rule {
    override fun validStrings(ruleMap: Map<Int, Rule>) : List<String> {
        val splitRules = rules.split(" ").filter{it.isNotEmpty() }.toMutableList()

        var result = mutableListOf("")
        do {
            val nextResult: MutableList<String> = mutableListOf()
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
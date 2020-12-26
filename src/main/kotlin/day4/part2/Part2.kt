package day4

val eyes = arrayListOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

fun main()
{
    println(data.map { it.split(" ", "\n") }.filter{it.count{s->s[0]!='c'}==7}.filter{valid(it)}.size)
}

fun valid(str: List<String>): Boolean {
    var result = true
    for (s in str) {
        val t = s.split(":")
        when (t[0]) {
            "byr" -> result = result && (t[1].toInt() in 1920..2002)
            "iyr" -> result = result && (t[1].toInt() in 2010..2020)
            "eyr" -> result = result && (t[1].toInt() in 2020..2030)
            "hgt" -> result = result && hgt(t[1])
            "hcl" -> result = result && t[1].matches(Regex("#[0-9a-f]{6}+"))
            "ecl" -> result = result && eyes.contains(t[1])
            "pid" -> result = result && t[1].matches(Regex("[0-9]{9}+"))
        }
    }
    return result
}

fun hgt(str: String): Boolean {
    if (str.endsWith("in")) {
        return str.take(str.length-2).toInt() in 59..76
    } else if (str.endsWith("cm")) {
        return str.take(str.length-2).toInt() in 150..193
    }
    return false
}
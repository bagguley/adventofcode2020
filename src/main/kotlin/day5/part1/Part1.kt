package day5

fun main() {
    val toInt : String.() -> Int = {Integer.valueOf(this.replace(Regex("[FL]"), "0").replace(Regex("[BR]"), "1"), 2)}
    println(data.map{ it.take(7).toInt() * 8 + it.takeLast(3).toInt()}.maxOrNull())
}




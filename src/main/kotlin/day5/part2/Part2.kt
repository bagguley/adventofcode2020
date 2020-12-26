package day5

fun main() {
    val bin : String.() -> Int = {Integer.valueOf(this.replace(Regex("[FL]"), "0").replace(Regex("[BR]"), "1"), 2)}
    val row = data.groupBy{it.take(7)}.filter{it.value.size==7}.entries.first()
    println(row.key.bin() * 8 + listOf(7, 6, 5, 4, 3, 2, 1, 0).minus(row.value.map{it.takeLast(3).bin()})[0])
}

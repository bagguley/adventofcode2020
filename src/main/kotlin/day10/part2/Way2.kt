package day10.part2

class Way2(private val number:Int, var possibilities:Long = 1) {
    val next:MutableList<Way2> = mutableListOf()

    override fun toString() = "$number:${next.size}:$possibilities"

    fun add(way: Way2) = next.add(way)
}
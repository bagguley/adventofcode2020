package day10.part2

class Way(val number:Int, val next:List<Int>, var possibilities:Long = 1) {
    override fun toString() =  "$number:${next.size}:$possibilities"
}
package day12.part1

class North(val num: Int) : Operation {
    override fun apply(boat: Boat) {
        boat.position.y -= num
    }
}
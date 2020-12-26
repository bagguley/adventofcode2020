package day12.part1

class West(val num: Int) : Operation {
    override fun apply(boat: Boat) {
        boat.position.x -= num
    }
}
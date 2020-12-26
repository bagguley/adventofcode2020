package day12.part1

class South(val num: Int) : Operation {
    override fun apply(boat: Boat) {
        boat.position.y += num
    }
}
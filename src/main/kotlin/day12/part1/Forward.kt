package day12.part1

class Forward(val num: Int) : Operation {
    override fun apply(boat: Boat) {
        boat.position.x += num*(boat.direction.x)
        boat.position.y += num*(boat.direction.y)
    }
}
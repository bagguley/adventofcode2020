package day12.part2

class Forward(val num: Int) : Operation {
    override fun apply(boat: Boat) {
        boat.position.x += num*(boat.waypoint.x)
        boat.position.y += num*(boat.waypoint.y)
    }
}
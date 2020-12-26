package day12.part2

class South(val num: Int) : Operation {
    override fun apply(boat: Boat) {
        boat.waypoint.y += num
    }
}
package day12.part2

class East(val num: Int) : Operation {
    override fun apply(boat: Boat) {
        boat.waypoint.x += num
    }
}
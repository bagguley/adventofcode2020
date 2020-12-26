package day12.part2

class North(val num: Int) : Operation {
    override fun apply(boat: Boat) {
        boat.waypoint.y -= num
    }
}
package day12.part2

class West(val num: Int) : Operation {
    override fun apply(boat: Boat) {
        boat.waypoint.x -= num
    }
}
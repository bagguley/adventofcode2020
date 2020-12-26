package day12.part2

class RotateLeft(val num: Int) : Operation {
    override fun apply(boat: Boat) {
        boat.waypoint.rotate(-num)
    }
}
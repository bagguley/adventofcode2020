package day12.part1

class RotateLeft(val num: Int) : Operation {
    override fun apply(boat: Boat) {
        boat.direction.rotate(-num)
    }
}
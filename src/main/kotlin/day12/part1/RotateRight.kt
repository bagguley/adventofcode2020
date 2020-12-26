package day12.part1

class RotateRight(val num: Int) : Operation {
    override fun apply(boat: Boat) {
        boat.direction.rotate(num)
    }
}
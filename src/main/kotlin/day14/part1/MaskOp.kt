package day14.part1

class MaskOp(private val value: String) : Operation {
    override fun apply(computer: Computer) {
        computer.setMask(value)
    }

    override fun toString(): String {
        return value
    }
}
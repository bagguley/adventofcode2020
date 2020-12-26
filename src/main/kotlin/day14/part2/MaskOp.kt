package day14.part2

class MaskOp(private val value: String) : Operation {
    override fun apply(computer: Computer) {
        computer.setMask(value)
    }
}
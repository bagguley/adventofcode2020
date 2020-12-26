package day14.part1

class MemOp(private val address: Int, private val value: Int) : Operation {
    override fun apply(computer: Computer) {
        val memory = computer.get(address)
        val mask = computer.getMask()
        memory.write(value, mask)
    }

    override fun toString(): String {
        return "$address, $value"
    }
}
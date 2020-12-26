package day14.part2

class Memory {
    private val value = CharArray(36){'0'}

    fun read(): Long = value.parseLong()

    fun write(write: Int) {
        write.toString(2).padStart(36, '0').reversed().toCharArray().copyInto(value)
    }
}

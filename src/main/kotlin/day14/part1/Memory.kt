package day14.part1

class Memory(private val value: CharArray) {
    fun write(write: Int, mask: String) {
        val binary = write.toString(2).reversed()

        for (i in mask.indices) {
            if (mask[i] != 'X') value[i] = mask[i]
        }

        for (i in binary.indices) {
            if (mask[i] == 'X') value[i] = binary[i]
        }

        for (i in binary.length until value.size) {
            if (mask[i] == 'X') value[i] = '0'
        }
    }

    fun read(): CharArray {
        return value
    }
}
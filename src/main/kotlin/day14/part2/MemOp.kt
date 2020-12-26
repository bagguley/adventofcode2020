package day14.part2

import kotlin.math.pow

class MemOp(private val address: Int, private val value: Int) : Operation {
    override fun apply(computer: Computer) {
        val maskedAddress = maskedAddress(computer.getMask())

        permuteAddresses(maskedAddress).forEach { computer.get(it.parseLong()).write(value) }
    }

    /**
     * Permute an address, replacing the X's with all the binary combinations
     */
    private fun permuteAddresses(maskedAddress: CharArray): List<CharArray> {
        val floatingCount = maskedAddress.count { it == 'X' }
        val numPermutations = 2.0.pow(floatingCount).toInt()
        val binPerms:List<CharArray> = List(numPermutations) { it.toString(2).reversed().padEnd(floatingCount, '0').toCharArray() }
        val xIndexes = maskedAddress.mapIndexed{ i, c -> Pair(i, c) }.filter { it.second == 'X' }.map { it.first }.toList()

        val addresses = mutableListOf<CharArray>()
        binPerms.forEach {
            val newAddrArray = maskedAddress.copyOf()
            xIndexes.forEachIndexed{idx, v -> newAddrArray[v] = it[idx]}
            addresses.add(newAddrArray)
        }

        return addresses
    }

    /**
     * Apply the mask to the address
     * 1 -> 1
     * X -> X
     * 0 -> Unchanged
     */
    private fun maskedAddress(mask: String): CharArray {
        val addrArray = addressToArray()

         mask.indices.forEach {
             when (mask[it]) {
                '1' -> addrArray[it] = '1'
                'X' -> addrArray[it] = 'X'
            }
        }

        return addrArray
    }

    /**
     * Convert the address Int into a 36 bit representation
     */
    private fun addressToArray(): CharArray {
        return address.toString(2).reversed().padEnd(36).toCharArray()
    }
}

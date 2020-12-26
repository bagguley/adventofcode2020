package day3.part2

import day3.data

fun main() {
    println(arrayOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2).foldRight(1L){x, a -> calc2b(x.first, x.second) * a})
}

fun calc2b(right: Int, down: Int) : Long {
    return data.filterIndexed{ a, b->a%down==0 && b[((a/down)*right)%31]=='#'}.sumOf { 1L }
}
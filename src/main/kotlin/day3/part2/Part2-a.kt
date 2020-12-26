package day3.part2

import day3.data

fun main() {
    println(calc2a(1,1) * calc2a(3,1) * calc2a(5,1) * calc2a(7,1) * calc2a(1,2))
}

fun calc2a(right: Int, down: Int) : Long {
    return data.filterIndexed{ a, b->a%down==0 && b[((a/down)*right)%31]=='#'}.sumOf { 1L }
}
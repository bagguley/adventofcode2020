package day3.part1

import day3.data

fun main() {
    print(data.filterIndexed{ a, b->b[(a*3)%31]=='#'}.count())
}
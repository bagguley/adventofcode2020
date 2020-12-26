package day6.part2

import day6.data

fun main() {
    println(data.map{ it.split("\n")}.map{it.map{it.toSet()}.reduce{ a, b->a.intersect(b)}.size}.sum())
}

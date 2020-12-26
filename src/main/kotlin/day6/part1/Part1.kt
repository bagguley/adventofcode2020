package day6.part1

import day6.data

fun main() {
    println(data.map{it.toSet().minus('\n').size}.reduce{a, b -> a+b})
}
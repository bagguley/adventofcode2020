package day1.part2

import day1.data

fun main() {
    println(sequence{(0..data.size-3).forEach{a->(1..data.size-2).forEach{b->(2..data.size-1).forEach{c->if(data[a]+data[b]+data[c]==2020) yield(data[a]*data[b]*data[c])}}}}.first())

    println(sequence{data.forEach{a->data.minus(a).forEach{b->data.minus(a).minus(b).forEach{c->if(a+b+c==2020) yield(a*b*c)}}}}.first())

    calc()
}

fun calc() {
    val input = data.toMutableList()
    val result = mutableListOf<Int>()
    while (input.size > 2) {
        val x = input.removeAt(0)
        val tail = input.toMutableList()
        while (tail.size > 1) {
            val y = tail.removeAt(0)
            tail.find { it + x + y == 2020 }?.let { result.add(it * x * y) }
        }
    }
    result.forEach { println(it) }
}
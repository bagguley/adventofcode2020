package day1.part1

import day1.data

fun main() {
    println(sequence {for(a in 0..data.size-2) { for (b in a+1 until data.size) { if (data[a] + data[b] == 2020) yield(data[a] * data[b])} } }.first())

    println((0..data.size-2).asSequence().flatMap{a->(a until data.size).map{ b->data[a] to data[b]}}.find{(x,y)->x+y==2020}?.let{(x,y)->x*y})

    println((0..data.size-2).asSequence().flatMap{a->(a until data.size).map{data[it]}.filter{b->2020-b==data[a]}}.first().let{it*(2020-it)})

    println(data.find{data.minus(it).contains(2020-it)}?.let{(2020-it)*it})

    println(calc())
}

fun calc() {
    val input = data.toMutableList()
    val result = mutableListOf<Pair<Int,Int>>()
    while (input.size > 1) {
        val x = input.removeAt(0)
        input.find { it + x == 2020 }?.let { result.add(x to it) }
    }
    result.forEach { println(it.first * it.second) }
}

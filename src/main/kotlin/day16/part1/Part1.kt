package day16.part1

import day16.data
import kotlin.streams.toList

fun main() {
    val input = data

    val rules = input[0].split("\n").map { parseRule(it) }

    val nearBy = input[2].split("\n").drop(1).map{it.split(",").map { it.toInt() }}

    println(nearBy.flatMap { findInvalid(it, rules) }.sum())
}

fun findInvalid(list: List<Int>, rules:List<Rule>): List<Int> {
    return list.stream().filter { t -> rules.stream().noneMatch() { it.isInRange(t) }  }.toList()
}
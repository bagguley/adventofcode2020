package day21.part1

import day21.data

val allergenMap = mutableMapOf<String, MutableSet<String>>()
val ingredientSet = mutableSetOf<String>()
val ingredientList = mutableListOf<String>()

fun main() {
    load(data)

    val nonAllergen = mutableSetOf<String>()
    nonAllergen.addAll(ingredientSet)
    for (p in allergenMap) {
        nonAllergen.removeAll(p.value)
    }

    println(nonAllergen.size)
    println(allergenMap)

    println(ingredientList.count{ nonAllergen.contains(it)})
}

fun load(data: List<String>) {
    for (d in data) {
        val split = d.split(" (")
        val ingredients = split[0].split(" ")
        val allergens = split[1].drop(9).dropLast(1).split(Regex(",* "))

        ingredientSet.addAll(ingredients)
        ingredientList.addAll(ingredients)

        for (a in allergens) {
            val set = allergenMap.getOrPut(a){ mutableSetOf()}
            if (set.isEmpty()) {
                set.addAll(ingredients)
            } else {
                set.retainAll(ingredients)
            }
        }
    }
}
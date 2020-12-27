package day21.part2

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

    // Go through each value, keep ones of list size = 1
    val definiteAllergen = mutableSetOf<String>()
    for (a in allergenMap) {
        if (a.value.size == 1) {
            definiteAllergen.add(a.value.first())
        }
    }
    // Remove from other lists
    for (a in allergenMap) {
        if (a.value.size != 1) {
            a.value.removeAll(definiteAllergen)
        }
    }
    for (a in allergenMap) {
        if (a.value.size == 1) {
            definiteAllergen.add(a.value.first())
        }
    }
    // Remove from other lists
    for (a in allergenMap) {
        if (a.value.size != 1) {
            a.value.removeAll(definiteAllergen)
        }
    }
    for (a in allergenMap) {
        if (a.value.size == 1) {
            definiteAllergen.add(a.value.first())
        }
    }
    // Remove from other lists
    for (a in allergenMap) {
        if (a.value.size != 1) {
            a.value.removeAll(definiteAllergen)
        }
    }

    val result = mutableListOf<MutableMap.MutableEntry<String, MutableSet<String>>>()
    for (a in allergenMap) {
        result.add(a)
    }
    result.sortBy { it.key }
    for (r in result) {
        print(r.value.first()+",")
    }
    println()

    println(allergenMap)
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
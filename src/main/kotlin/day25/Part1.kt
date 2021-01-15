package day25


val cardKey = 15733400L
val doorKey = 6408062L

fun main() {

    val cardLoop = card(7L)
    val doorLoop = door(7L)

    val enc = enc(doorLoop, cardKey)
    println(enc)
}

fun transform(a: Long, subj: Long): Long {
    var x = a
    x *= subj
    x %= 20201227L
    return x
}

fun card(subj: Long): Long {
    var count = 0L
    var x = 1L
    while (x != cardKey) {
        x = transform(x, subj)
        count++
    }
    return count
}

fun door(subj: Long): Long {
    var count = 0L
    var x = 1L
    while (x != doorKey) {
        x = transform(x, subj)
        count++
    }
    return count
}

fun enc(loop :Long, subj: Long):Long {
    var x = 1L
    for (i in 1..loop) {
        x = transform(x, subj)
    }
    return x
}
package day22.part2

import day22.*

fun main() {
    val input = data

    val player1 = input[0].split("\n").drop(1).map{it.toInt()}
    val player2 = input[1].split("\n").drop(1).map{it.toInt()}

    val game = Game(player1, player2)
    val winner = game.playGame()

    if (winner == 1) {
        println("Player 1 has won")
        println(game.player1)
        println(score(game.player1))
    } else {
        println("Player 2 has won")
        println(game.player2)
        println(score(game.player2))
    }
}

fun score(winner: List<Int>): Long {
    var x = winner.size
    var score = 0L
    for (w in winner) {
        score += x * w
        x--
    }
    return score
}
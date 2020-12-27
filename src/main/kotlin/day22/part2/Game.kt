package day22.part2

class Game(player1: List<Int>, player2: List<Int>) {
    private val previousPlayer1 = mutableListOf<List<Int>>()
    private val previousPlayer2 = mutableListOf<List<Int>>()
    val player1 = player1.toMutableList()
    val player2 = player2.toMutableList()

    fun playGame(): Int {
        while (player1.isNotEmpty() && player2.isNotEmpty()) {
            playRound()
        }
        if (player1.isEmpty()) {
            //println("Player 2 wins game")
            return 2
        } else {
            //println("Player 1 wins game")
            return 1
        }
    }

    private fun playRound() {
        if (previousPlayer1.contains(player1) || previousPlayer2.contains(player2)) {
            // Previous layout detected, player 1 wins
            val c1 = player1.removeFirst()
            val c2 = player2.removeFirst()

            //println("Player 1 wins - a")
            player1.add(c1)
            player1.add(c2)
        } else {
            // Unique combination, let's play

            // Record the cards
            previousPlayer1.add(player1.toList())
            previousPlayer2.add(player2.toList())

            // Draw the cards
            val c1 = player1.removeFirst()
            val c2 = player2.removeFirst()

            if (c1 <= player1.size && c2 <= player2.size) {
                // Recurse
                val p1Sub = player1.take(c1)
                val p2Sub = player2.take(c2)
                //println("Playing sub game")
                val subGame = Game(p1Sub, p2Sub)
                val winner = subGame.playGame()

                if (winner == 1) {
                    //println("Player 1 wins - b")
                    player1.add(c1)
                    player1.add(c2)
                } else {
                    //println("Player 2 wins - b")
                    player2.add(c2)
                    player2.add(c1)
                }
            } else {
                // Winner is the one with the highest card
                if (c1 > c2) {
                    //println("Player 1 wins - c")
                    player1.add(c1)
                    player1.add(c2)
                } else {
                    //println("Player 2 wins - c")
                    player2.add(c2)
                    player2.add(c1)
                }
            }
        }
    }
}
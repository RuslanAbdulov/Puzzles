package gameboard.games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    return permutation.asSequence()
            .withIndex()
            .flatMap { i ->  permutation.asSequence()
                    .withIndex()
                    .filter { j -> i.index < j.index }
                    .map { j -> i.value to j.value }
            }
            .count { (i, j) -> i > j }
            .isEven()
}

fun Int.isEven(): Boolean =  this % 2 == 0
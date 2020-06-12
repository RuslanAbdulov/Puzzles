package leaderboard

import java.io.FileNotFoundException
import java.io.FileReader
import java.util.*


fun main(args: Array<String>) {
    var scan = Scanner(System.`in`)

    try {
        scan = Scanner(FileReader("Kotlin/src/leaderboard/input.txt"))
    } catch (e: FileNotFoundException) {
        //use System.in
    }

    val scoresCount = scan.nextLine().trim().toInt()

    val scores = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

    val aliceCount = scan.nextLine().trim().toInt()

    val alice = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

    val result = climbingLeaderboard(scores, alice)

    println(result.joinToString("\n"))

    println("======correcct:=====")
    println(Array(alice.size){ i -> findPlace(scores, alice[i])}.joinToString("\n"))
}


// Complete the climbingLeaderboard function below.
fun climbingLeaderboard(scores: Array<Int>, alice: Array<Int>): Array<Int> {
    val optimalSolution = OptimalSolution(scores)
    return Array(alice.size){ i -> optimalSolution.findPlace(alice[i])}
}


fun findPlace(scores: Array<Int>, newScore: Int): Int {
    var place = 1
    var prevScore: Int? = null

    for (score in scores) {
        if (newScore >= score) {
            return place
        }
        if (prevScore == null || prevScore > score) {
            place++
        }
        prevScore = score
    }

    return place
}


class OptimalSolution(private val scores: Array<Int>) {
    private val denseRank: Array<Int> = calcDenseRank(scores)

    fun findPlace(score: Int): Int {
        val index = findIndex(scores, score, 0, scores.lastIndex)
        if (index >= scores.size) {
            return denseRank.last() + 1
        }
        return denseRank[index]
    }

    private fun findIndex(array: Array<Int>, newElement: Int, from: Int, to: Int): Int {
        val index = (to - from)/2
        //difference is less than 2
        if (index == 0) {
            return when {
                array[from] <= newElement -> from
                array[to] <= newElement -> to
                else -> to + 1
            }
        }
        //reversed binary search
        return when {
            array[index] < newElement -> findIndex(array, newElement, from,  from + index)
            array[index] > newElement -> findIndex(array, newElement, to - index, to)
            else -> index
        }
    }

    private fun calcDenseRank(scores: Array<Int>): Array<Int> {
        val ranks: Array<Int> = Array(scores.size){0}

        var place = 0
        var prevScore: Int? = null

        for ((index, score) in scores.withIndex()) {
            if (prevScore == null || prevScore > score) {
                place++
            }
            ranks[index] = place
            prevScore = score
        }

        return ranks
    }

}

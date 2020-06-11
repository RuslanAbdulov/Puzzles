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
}


// Complete the climbingLeaderboard function below.
fun climbingLeaderboard(scores: Array<Int>, alice: Array<Int>): Array<Int> {
    return Array(alice.size){ i -> findPlace(scores, alice[i])}
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



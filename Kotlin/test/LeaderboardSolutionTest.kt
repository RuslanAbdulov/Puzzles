package leaderboard

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class LeaderboardSolutionTest {
    private val testScores = arrayOf(100, 90, 90, 80, 75, 60)

    @Test
    fun testPlace1() {
        val result = findPlace(testScores, 102)
        assertEquals(1, result)
    }

    @Test
    fun testPlace2() {
        val result = findPlace(testScores, 90)
        assertEquals(2, result)
    }

    @Test
    fun testPlace3() {
        val result = findPlace(testScores, 77)
        assertEquals(4, result)
    }

    @Test
    fun testPlace4() {
        val result = findPlace(testScores, 65)
        assertEquals(5, result)
    }

    @Test
    fun testPlace5() {
        val result = findPlace(testScores, 50)
        assertEquals(6, result)
    }
}


package gameboard.games.game2048

import gameboard.board.Cell
import gameboard.board.Direction
import gameboard.board.GameBoard
import gameboard.board.createGameBoard
import gameboard.games.game.Game

/*
 * Your task is to implement the game 2048 https://en.wikipedia.org/wiki/2048_(video_game).
 * Implement the utility methods below.
 *
 * After implementing it you can try to play the game running 'PlayGame2048'.
 */
fun newGame2048(initializer: Game2048Initializer<Int> = RandomGame2048Initializer): Game =
        Game2048(initializer)

class Game2048(private val initializer: Game2048Initializer<Int>) : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        repeat(2) {
            board.addNewValue(initializer)
        }
    }

    override fun canMove() = board.any { it == null }

    override fun hasWon() = board.any { it == 2048 }

    override fun processMove(direction: Direction) {
        if (board.moveValues(direction)) {
            board.addNewValue(initializer)
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }
}

/*
 * Add a new value produced by 'initializer' to a specified cell in a board.
 */
fun GameBoard<Int?>.addNewValue(initializer: Game2048Initializer<Int>) {
    val (cell, value) = run(initializer::nextValue) ?: return
    set(cell, value)
}

/*
 * Update the values stored in a board,
 * so that the values were "moved" in a specified rowOrColumn only.
 * Use the helper function 'moveAndMergeEqual' (in Game2048Helper.kt).
 * The values should be moved to the beginning of the row (or column),
 * in the same manner as in the function 'moveAndMergeEqual'.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValuesInRowOrColumn(rowOrColumn: List<Cell>): Boolean {
    val values = rowOrColumn.map {cell -> this[cell]}
    val newValues = values.moveAndMergeEqual{value -> value + value}
    val moveMap = rowOrColumn.zip(newValues) {cell, value -> cell to value}.toMap()
    var anyChange = false
    rowOrColumn.forEach{cell ->
        if (moveMap[cell] != this[cell])
            anyChange = true
        this[cell] = moveMap[cell]
    }

    return anyChange

}

/*
 * Update the values stored in a board,
 * so that the values were "moved" to the specified direction
 * following the rules of the 2048 game .
 * Use the 'moveValuesInRowOrColumn' function above.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValues(direction: Direction): Boolean {
    val range = when (direction) {
        Direction.DOWN, Direction.RIGHT -> width downTo 1
        Direction.UP, Direction.LEFT -> 1..width
    }

    var anyChange = false

    for (i in 1..width) {
        val rowOrColumn = when (direction) {
            Direction.RIGHT, Direction.LEFT -> getRow(i, range)
            Direction.UP, Direction.DOWN -> getColumn(range, i)
        }
        anyChange = moveValuesInRowOrColumn(rowOrColumn) || anyChange
    }

    return anyChange

}
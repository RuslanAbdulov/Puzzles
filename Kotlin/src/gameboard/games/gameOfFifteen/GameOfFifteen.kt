package gameboard.games.gameOfFifteen

import gameboard.board.Direction
import gameboard.board.GameBoard
import gameboard.board.createGameBoard
import gameboard.games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
        GameOfFifteen(initializer)


class GameOfFifteen(private val initializer: GameOfFifteenInitializer): Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        board.getAllCells().zip(initializer.initialPermutation)
                .forEach {(cell, value) -> board[cell] = value}
    }

    override fun canMove(): Boolean = board.any { it == null }

    override fun hasWon(): Boolean = board.getAllCells()
            .filter { board[it] != null }
            .withIndex()
            .none { (index, cell) -> index + 1 != board[cell] }


    override fun processMove(direction: Direction) {
        val emptyCell = board.find { it == null }!!
        with(board) {
            emptyCell.getNeighbour(direction.reversed())?.let { neighbour ->
                board[emptyCell] = board[neighbour]
                board[neighbour] = null
            }
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }

}
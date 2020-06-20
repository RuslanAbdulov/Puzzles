package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width)

val directionMask = mapOf (
        UP to Cell(-1, 0),
        DOWN to Cell(1, 0),
        RIGHT to Cell(0, 1),
        LEFT to Cell(0, -1))

class SquareBoardImpl(override val width: Int) : SquareBoard {
    private val cells: Array<Array<Cell>> = Array(width) {i -> Array(width) {j -> Cell(i + 1, j + 1) } }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (inBorders(i, j))
            cells[i-1][j-1]
        else
            null
    }

    override fun getCell(i: Int, j: Int): Cell {
        return getCellOrNull(i, j) ?: throw IllegalArgumentException()
    }

    override fun getAllCells(): Collection<Cell> {
        return cells.flatMap {row -> row.map {cell -> cell }}.toList()
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return jRange.filter {j -> inBorders(i, j)}
                .map {j -> cells[i-1][j-1]}.toList()
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return iRange.filter {i -> inBorders(i, j)}
                .map {i -> cells[i-1][j-1]}.toList()
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        val mask = directionMask[direction] ?: return null
        val neighbour = Cell(this.i + mask.i, this.j + mask.j)

        return if (inBorders(neighbour))
            getCell(neighbour.i, neighbour.j)
        else
            null
    }

    private fun inBorders(i: Int): Boolean = i in 1..width
    private fun inBorders(i: Int, j: Int): Boolean = inBorders(i) && inBorders(j)
    private fun inBorders(cell: Cell): Boolean = inBorders(cell.i, cell.j)
}

class GameBoardImpl<T>(override val width: Int): GameBoard<T>,
        SquareBoard by SquareBoardImpl(width) {
    val map = mutableMapOf<Cell, T>()

    override fun get(cell: Cell): T? {
        TODO("Not yet implemented")
    }

    override fun set(cell: Cell, value: T?) {
        TODO("Not yet implemented")
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        TODO("Not yet implemented")
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        TODO("Not yet implemented")
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }


}
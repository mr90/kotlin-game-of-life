package src.main.kotlin

import java.lang.IllegalArgumentException

open class BoardImpl<T>(override val width: Int, override val height: Int, private val initValue : T) : Board<T> {

    private val board : Array<Array<Cell<T>>>

    init {
        board = Array(width) { i ->
            Array(height) { j ->
                Cell(i, j, initValue)
            }
        }
        println("Board created: \n" + toString())
    }

    override fun toString(): String {
        return board.joinToString(separator="\n", prefix = "[", postfix = "]") {row : Array<Cell<T>> ->
            row.joinToString(separator = " ") {
                    cell : Cell<T> ->  cell.toString()}
        }
    }

    fun validateCellPosition(i: Int, j: Int) {
        if (i<0 || j<0)
            throw IllegalArgumentException("Rows and Columns should be positive numbers")
        else if (i>=width || j>=height)
            throw IllegalArgumentException("Row and/or Column exceed the current size")
    }

    override fun getCell(i: Int, j: Int): Cell<T> {
        validateCellPosition(i, j)
        return board[i][j]
    }

    override fun setCell(i: Int, j: Int, value: T) {
        validateCellPosition(i, j)
        board[i][j].value = value
    }

    override fun getAllCells(): Collection<Cell<T>> {
        return board.flatten()
    }

    override fun Cell<T>.getNeighbour(neighbour: NeighbourPosition): Cell<T>? {
        return try {
            when (neighbour) {
                NeighbourPosition.NORTH -> getCell(i-1, j)
                NeighbourPosition.NORTHEAST -> getCell(i-1, j-1)
                NeighbourPosition.NORTHWEST -> getCell(i-1, j+1)
                NeighbourPosition.SOUTH -> getCell(i+1, j)
                NeighbourPosition.SOUTHEAST -> getCell(i+1, j-1)
                NeighbourPosition.SOUTHWEST -> getCell(i+1, j+1)
                NeighbourPosition.EAST -> getCell(i, j-1)
                else -> getCell(i, j+1)
            }
        } catch (e : IllegalArgumentException){
            null
        }
    }

    override fun Cell<T>.getNeighbourhood(): List<Cell<T>>? {
        return enumValues<NeighbourPosition>().map { getNeighbour(it) }.filterNotNull()
    }

}

fun <T> createBoard(width: Int, height: Int, initValue: T): Board<T> = BoardImpl<T>(width, height, initValue)
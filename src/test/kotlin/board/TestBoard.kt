package board

import board.Cell
import board.createBoard
import org.junit.Assert
import org.junit.Test
import java.lang.IllegalArgumentException

class TestBoard {

    fun Collection<Cell<String>>.positionToString() =
        joinToString(prefix = "[", postfix = "]", separator = ", ") { it.positionToString() }

    @Test
    fun test00GetCell() {
        val board = createBoard<String>(2, 2, "initValue")
        val cell = board.getCell(0, 1)
        Assert.assertEquals(0, cell.i)
        Assert.assertEquals(1, cell.j)
        Assert.assertEquals("initValue", cell.value)
    }

    @Test
    fun test01SetCell() {
        val gameBoard = createBoard<String>(2,2, "initValue")
        gameBoard.setCell(0,0, "changedValue")
        Assert.assertEquals("changedValue", gameBoard.getCell(0,0).value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test02NoCell() {
        val board = createBoard<String>(2,2, "initValue")
        board.getCell(2, 2)
    }

    @Test
    fun test03AllCells() {
        val board = createBoard<String>(3,3, "initValue")
        val cells = board.getAllCells().sortedWith(compareBy<Cell<String>> { it.i }.thenBy { it.j })
        Assert.assertEquals("Wrong result for 'getAllCells()' for the board of width 3.",
            "[(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2)]",
            cells.positionToString())
    }

    @Test
    fun test04Neighbour() {
        val board = createBoard<String>(2,2, "initValue")
        with(board) {
            val cell = getCell(0, 0)
            Assert.assertNotNull(cell)
            Assert.assertEquals(null, cell.getNeighbour(NeighbourPosition.NORTH)?.positionToString())
            Assert.assertEquals(null, cell.getNeighbour(NeighbourPosition.NORTHEAST)?.positionToString())
            Assert.assertEquals(null, cell.getNeighbour(NeighbourPosition.NORTHWEST)?.positionToString())
            Assert.assertEquals(null, cell.getNeighbour(NeighbourPosition.EAST)?.positionToString())
            Assert.assertEquals(null, cell.getNeighbour(NeighbourPosition.SOUTHEAST)?.positionToString())
            Assert.assertEquals("(0, 1)", cell.getNeighbour(NeighbourPosition.SOUTH)?.positionToString())
            Assert.assertEquals("(1, 1)", cell.getNeighbour(NeighbourPosition.SOUTHWEST)?.positionToString())
            Assert.assertEquals("(1, 0)", cell.getNeighbour(NeighbourPosition.WEST)?.positionToString())
        }
    }

    @Test
    fun test05Neighbourhood() {
        val board = createBoard<String>(2,2, "initValue")
        with(board) {
            val cell = getCell(0, 0)
            Assert.assertNotNull(cell)
            Assert.assertEquals("[(0, 1), (1, 0), (1, 1)]",
                cell.getNeighbourhood()?.sortedWith(compareBy<Cell<String>> { it.i }.thenBy { it.j })
                    ?.positionToString())
        }
    }
}

package game

import board.Cell
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class TestGameOfLife {

    val boardWidth = 10
    val boardHeight = 10

    @Test
    fun test00Initialize_Empty(){
        val initializer: IGameOfLifeInitializer = mockk()
        every { initializer.boardWidth } returns boardWidth
        every { initializer.boardHeight } returns boardHeight
        every { initializer.getLive(any()) } returns listOf()

        val gameOfLife = GameOfLifePresenter(initializer)
        gameOfLife.initialize(0)

        assertEquals(0, gameOfLife.getCountOfLiveCell())
    }

    @Test
    fun test00Initialize_Full(){
        val initializer: IGameOfLifeInitializer = mockk()
        every { initializer.boardWidth } returns boardWidth
        every { initializer.boardHeight } returns boardHeight
        every { initializer.getLive(any()) } returns
                (0 until 10).map { i -> (0 until 10).map { j -> Cell(i, j, State.LIVE) }}.flatten()

        val gameOfLife = GameOfLifePresenter(initializer)
        gameOfLife.initialize(100)

        assertEquals(100, gameOfLife.getCountOfLiveCell())
    }

    @Test
    fun test00Initialize_Fifty(){
        val initializer: IGameOfLifeInitializer = mockk()
        every { initializer.boardWidth } returns boardWidth
        every { initializer.boardHeight } returns boardHeight
        every { initializer.getLive(any()) } returns
                (0 until 10).map { i -> (0 until 5).map { j -> Cell(i, j, State.LIVE) }}.flatten()

        val gameOfLife = GameOfLifePresenter(initializer)
        gameOfLife.initialize(50)

        assertEquals(50, gameOfLife.getCountOfLiveCell())
    }
}
package game

import java.awt.Color
import javax.swing.JFrame
import javax.swing.WindowConstants

fun main() {
    with(JFrame()) {
        title = "Game Of Life"
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        val boardWidth = 20
        val boardHeight = 8
        val settings = GameOfLifeSettings()

        add(GameOfLifeView(RandomGameOfLifeInitializer(boardWidth, boardHeight), settings))

        val panelWidth = boardWidth*settings.cellSize + (boardWidth+1)*settings.cellMargin
        val panelHeight = boardHeight*settings.cellSize + (boardHeight+1)*settings.cellMargin + FRAME_HEADER_HEIGHT

        setSize(panelWidth, panelHeight)
        isResizable = false

        setLocationRelativeTo(null)
        isVisible = true
    }
}
private val FRAME_HEADER_HEIGHT = 36
private val FONT_NAME = "Arial"
private val FONT_SIZE = 20
private val CELL_SIZE = 64
private val CELL_MARGIN = 16

private val BACKGROUND_COLOR = Color(0xDDDDDD)
private val LIVE_COLOR = Color(0x000000)
private val DEAD_COLOR = Color(0xFFFFFF)

data class GameOfLifeSettings(
                val fontName : String = FONT_NAME,
                val fontSize : Int = FONT_SIZE,
                val cellSize : Int = CELL_SIZE,
                val cellMargin : Int = CELL_MARGIN,
                val backgroundColor : Color = BACKGROUND_COLOR,
                val liveColor : Color = LIVE_COLOR,
                val deadColor : Color = DEAD_COLOR)



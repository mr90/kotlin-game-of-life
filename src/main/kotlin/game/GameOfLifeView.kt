package game

import java.awt.*
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JPanel

class GameOfLifeView(initializer: IGameOfLifeInitializer) : JPanel(), IGameOfLifeView {

    private val backgroundColor = Color(0xDDDDDD)
    private val liveColor = Color(0x000000)
    private val deadColor = Color(0xFFFFFF)
    private val game = GameOfLifePresenter(initializer, view = this)
    private val boardWidth = initializer.boardWidth
    private val boardHeight = initializer.boardHeight

    init {
        isFocusable = true
        addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (e.keyCode == KeyEvent.VK_SPACE)
                    if (game.isRunning())
                        game.stop()
                    else game.start()
            }
        })
        game.initialize()
    }

    fun getColor(state: State) =
        when(state) {
            State.LIVE -> liveColor
            else -> deadColor
        }

    override fun paint(g: Graphics) {
        super.paint(g)
        g.color = backgroundColor
        g.fillRect(0, 0, this.size.width, this.size.height)
        for (x in 0 until boardWidth) {
            for (y in 0 until boardHeight) {
                drawTile(g as Graphics2D, game.get(x, y), x, y)
            }
        }
    }

    private fun offsetCoors(arg: Int): Int {
        return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN
    }

    private fun drawTile(g: Graphics2D, value: State, x: Int, y: Int) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)

        val xOffset = offsetCoors(x)
        val yOffset = offsetCoors(y)
        g.color = getColor(value)
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14)
        g.color = backgroundColor
        val size = 24
        val font = Font(FONT_NAME, Font.BOLD, size)
        g.font = font

        val s = "($x, $y)"
        val fm = getFontMetrics(font)

        val w = fm.stringWidth(s)
        val h = -fm.getLineMetrics(s, g).baselineOffsets[2].toInt()

        g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2)

        g.font = Font(FONT_NAME, Font.PLAIN, 18)
    }

    override fun drawNextStep() {
        println(game.toString())
        repaint()
    }
}

private val FONT_NAME = "Arial"
private val TILE_SIZE = 64
private val TILES_MARGIN = 16

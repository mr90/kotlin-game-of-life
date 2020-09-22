package game

import java.awt.*
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JPanel

class GameOfLifeView(initializer: IGameOfLifeInitializer, val settings : GameOfLifeSettings) : JPanel(), IGameOfLifeView {

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
        game.initialize(60)
    }

    fun getColor(state: State) =
        when(state) {
            State.LIVE -> settings.liveColor
            else -> settings.deadColor
        }

    override fun paint(g: Graphics) {
        super.paint(g)
        g.color = settings.backgroundColor
        g.fillRect(0, 0, this.size.width, this.size.height)
        for (x in 0 until boardWidth) {
            for (y in 0 until boardHeight) {
                drawCell(g as Graphics2D, game.get(x, y), x, y)
            }
        }
    }

    private fun offsetCoors(arg: Int): Int {
        return arg * (settings.cellMargin + settings.cellSize) + settings.cellMargin
    }

    private fun drawCell(g: Graphics2D, value: State, x: Int, y: Int) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)

        val xOffset = offsetCoors(x)
        val yOffset = offsetCoors(y)
        g.color = getColor(value)
        g.fillRoundRect(xOffset, yOffset, settings.cellSize, settings.cellSize, 14, 14)
        g.color = settings.backgroundColor
        val font = Font(settings.fontName, Font.BOLD, settings.fontSize)
        g.font = font

        val s = "($x, $y)"
        val fm = getFontMetrics(font)

        val w = fm.stringWidth(s)
        val h = -fm.getLineMetrics(s, g).baselineOffsets[2].toInt()

        g.drawString(s, xOffset + (settings.cellSize - w) / 2, yOffset + settings.cellSize - (settings.cellSize - h) / 2 - 2)
    }

    override fun drawNextStep() {
        println(game.toString())
        repaint()
    }
}

package game

import javax.swing.JFrame
import javax.swing.WindowConstants

fun main() {
    with(JFrame()) {
        title = "Game Of Life"
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        setSize(700, 700)
        isResizable = false

        add(GameOfLifeView(RandomGameOfLifeInitializer(8, 8)))

        setLocationRelativeTo(null)
        isVisible = true
    }
}
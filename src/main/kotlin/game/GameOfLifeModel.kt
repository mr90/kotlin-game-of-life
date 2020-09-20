package game

import board.Board

data class GameOfLifeModel(val board: Board<State>, var step: Int = 0) {

    override fun toString(): String {
        return "Step:${step}\nBoard:\n${board}"
    }

}


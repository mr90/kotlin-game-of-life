package game

import board.Cell

interface IGameOfLifeInitializer {
    val boardWidth: Int
    val boardHeight: Int

    /*
     * percent: Percentage of live cells in the board.
     */
    fun getLive(percent : Int) : Collection<Cell<State>>
}

/*
 *   Completely random live cells generator
 */
class RandomGameOfLifeInitializer(override val boardWidth: Int, override val boardHeight: Int) : IGameOfLifeInitializer {
    val boardLenght = boardWidth*boardHeight;

    override fun getLive(percent: Int): Collection<Cell<State>> {
        val liveLenght = (if (percent<0) 0 else (if (percent>100) 100 else percent))*boardLenght/100
        return (1..boardLenght).shuffled().take(liveLenght).map { Cell((it-1)%boardWidth, (it-1)/boardWidth, State.LIVE) }
    }
}

/*
 *   Mixed predefined live cells generator (still life, oscillator, spaceships)
 */
class MixedGameOfLifeInitializer(override val boardWidth: Int, override val boardHeight: Int) : IGameOfLifeInitializer {
    override fun getLive(percent: Int): Collection<Cell<State>> {
        TODO("Not yet implemented")
    }
}
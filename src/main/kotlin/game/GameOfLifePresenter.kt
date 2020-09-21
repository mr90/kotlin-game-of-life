package game

import board.Board
import board.createBoard
import kotlinx.coroutines.*
import kotlinx.coroutines.swing.Swing

class GameOfLifePresenter(private val initializer: IGameOfLifeInitializer, private val view : IGameOfLifeView?) : IGameOfLifePresenter {
    private val model = GameOfLifeModel(createBoard(initializer.boardWidth, initializer.boardHeight, State.DEAD))

    private var gameLoop: Job? = null

    override fun initialize() {
        initializer.getLive(60).map{
            model.board.setCell(it.i, it.j, it.value)
        }
        println("Board Initialized:\n${model.board}")
    }

    override fun toString(): String {
        return "isRunning: ${isRunning()}\n$model"
    }

    override fun isRunning() = gameLoop!=null

    override fun get(i: Int, j: Int) : State = model.board.run { getCell(i, j).value }

    override fun start(){
        if (gameLoop != null) return

        gameLoop = GlobalScope.launch {
                while (isActive) {
                    model.board.processNextStep()
                    model.step = model.step.inc()
                    withContext(Dispatchers.Swing) {
                        view?.drawNextStep()
                    }
                    delay(1000)
                }
            }
    }

    override fun stop() = runBlocking {
        gameLoop?.cancel()
        gameLoop = null
    }

    fun Board<State>.processNextStep() = getAllCells().forEach{ cell ->
        val population = cell.getNeighbourhood()?.count{ neighbour -> neighbour.value== State.LIVE } ?: 0

        when(cell.value){
            State.LIVE -> if (population<2 || population>3) setCell(cell.i, cell.j, State.DEAD)
            else -> if (population==3) setCell(cell.i, cell.j, State.LIVE)
        }
    }
}
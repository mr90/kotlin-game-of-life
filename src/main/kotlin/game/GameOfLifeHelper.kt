package game

interface IGameOfLifePresenter {
    fun initialize(percentOfLife: Int)
    fun isRunning() : Boolean
    fun start()
    fun stop()
    fun get(i: Int, j: Int) : State
    fun getCountOfLiveCell() : Int
}

interface IGameOfLifeView {
    fun drawNextStep()
}

enum class State {
    LIVE, DEAD;
}
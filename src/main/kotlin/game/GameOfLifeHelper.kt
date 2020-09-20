package game

interface IGameOfLifePresenter {
    fun initialize()
    fun isRunning() : Boolean
    fun start()
    fun stop()
    fun get(i: Int, j: Int) : State
}

interface IGameOfLifeView {
    fun drawNextStep()
}

enum class State {
    LIVE, DEAD;
}
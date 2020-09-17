package src.main.kotlin

data class Cell<T>(val i: Int, val j: Int, var value: T) {
    override fun toString() = "{${positionToString()} = ${valueToString()}}"
    fun positionToString() = "($i, $j)"
    fun valueToString() = "$value"
}

enum class NeighbourPosition {
    NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST;
}

interface Board<T> {
    val width: Int
    val height: Int

    fun getCell(i: Int, j: Int): Cell<T>
    fun setCell(i: Int, j: Int, value: T)

    fun getAllCells(): Collection<Cell<T>>

    fun Cell<T>.getNeighbour(neighbour: NeighbourPosition): Cell<T>?
    fun Cell<T>.getNeighbourhood(): List<Cell<T>>?
}
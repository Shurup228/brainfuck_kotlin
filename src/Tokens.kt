/*
    Tokens represents code units from brainfuck e.g.
        Move -> >, <
        Print -> .
        Write -> ,
        OpenLoop -> [
        CloseLoop -> ]
        ChangeValue -> +, -

    Tokens implemented in order(and only) to use benefits of Kotlin type system.
*/
abstract class Token(var index:Int = 0) {
    open fun merge(toMerge: Token): Token? {
        return null
    }
}

data class Move(val value: Int) : Token() {
    // value -> how many cells to skip
    override fun merge(toMerge: Token): Move? {
        if (toMerge is Move) {
            return Move(value + toMerge.value)
        }
        return null
    }
}

object Print : Token() // value -> nothing because we don't have any values at compile time

object Write : Token() // value -> same here

data class ChangeValue(val value: Int) : Token() {
    // value -> how much to change the value of current cell
    override fun merge(toMerge: Token): Token? {
        if (toMerge is ChangeValue) {
            return ChangeValue(value + toMerge.value)
        }
        return null
    }
}

data class OpenLoop(var index, var closeLoopIndex: Int = 0) : Token(index)

data class CloseLoop(index: Int, var openLoopIndex: Int = 0) : Token(index)

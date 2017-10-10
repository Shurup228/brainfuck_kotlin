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
abstract class Token {
    open fun merge(toMerge: Token): Token? {
        return null
    }
}

class Move(val value: Int) : Token() {
    // value -> how many cells to skip
    override fun merge(toMerge: Token): Move? {
        if (toMerge is Move) {
            return Move(value + toMerge.value)
        }
        return null
    }
}

class Print : Token() // value -> nothing because we don't have any values at compile time

class Write : Token() // value -> same here

class ChangeValue(val value: Int) : Token() {
    // value -> how much to change the value of current cell
    override fun merge(toMerge: Token): Token? {
        if (toMerge is ChangeValue) {
            return ChangeValue(value + toMerge.value)
        }
        return null
    }
}

class OpenLoop(val closeLoopIndex: Int = 0) : Token()

class CloseLoop(val openLoopIndex: Int = 0) : Token()

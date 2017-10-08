import java.util.LinkedList

class Parser(private val toParse: String) {
    // TODO write code validator
    lateinit private var tokens: LinkedList<Token>

    private fun parse() {
        tokens = toParse.asSequence().filter {
            it in "-+><.,[]" // Ignoring all symbols except brainfuck instructions
        }.map {
            when (it) {
                '-' -> ChangeValue(-1)
                '+' -> ChangeValue(1)
                '>' -> Move(1)
                '<' -> Move(-1)
                '.' -> Print()
                ',' -> Write()
                '[' -> OpenLoop()
                else -> CloseLoop()
            }
        }.toCollection(tokens)
    }

    // This function merges row of same tokens in one
    // e.g. Move(1), Move(-1), Move(-1) will merge in Move(1 + (-1) + (-1))
    private fun optimize() {
    }

    private fun loopParse() {
        // TODO this too -_-
    }

    fun getTokens(): LinkedList<Token> {
        parse()
        optimize() // Invoking optimize first, because after optimization applied
        loopParse() // tokens will be shifted
        return tokens
    }
}
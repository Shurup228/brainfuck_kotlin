import java.util.LinkedList

class Parser(private val toParse: String) {
    // TODO write code validator
    private val tokens: LinkedList<Token> = LinkedList()

    private fun preParse() {
        var lastSym = ' ' // Initializing with some trash

        parser@ for (sym in toParse){
            val token = when (sym) {
                '>' -> Move(1)
                '<' -> Move(-1)
                '+' -> ChangeValue(1)
                '-' -> ChangeValue(-1)
                '.' -> Print()
                ',' -> Write()
                '[' -> OpenLoop()
                ']' -> CloseLoop()
                else -> { // Ignoring all other symbols
                    lastSym = sym
                    continue@parser
                }
            }

            // Basic optimisation for parts like ++++ -> ChangeValue(4)
            // e.g it merges parts that repeats in one token
            if (sym == lastSym && lastSym in "><+-") {
                when (sym) {
                    '+' -> {
                        val lastToken: ChangeValue = tokens[tokens.size - 1] as ChangeValue
                        lastToken.value += 1
                    }
                    '-' -> {
                        val lastToken: ChangeValue = tokens[tokens.size - 1] as ChangeValue
                        lastToken.value -= 1
                    }
                    '>' -> {
                        val lastToken: Move = tokens[tokens.size - 1] as Move
                        lastToken.value += 1
                    }
                    '<' -> {
                        val lastToken: Move = tokens[tokens.size - 1] as Move
                        lastToken.value -= 1
                    }
                }
            }

            tokens.add(token)
            lastSym = sym
        }
    }

    private fun loopParse(): LinkedList<Token> {
        // TODO implement this =)
    }

    fun parse(): LinkedList<Token> {
        preParse()
        loopParse()
        return tokens
    }
}
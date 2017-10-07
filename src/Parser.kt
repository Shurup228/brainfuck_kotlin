import java.util.LinkedList

class Parser(private val toParse: String) {
    // TODO write functions validate that will count brackets
    fun parse() : LinkedList<Token> {
        val tokens: LinkedList<Token> = LinkedList()
        var lastSym = ' '

        parsing@for (sym in toParse) {
            val token = when(sym) {
                '>' -> Move(1)
                '<' -> Move(-1)
                '+' -> ChangeValue(1)
                '-' -> ChangeValue(-1)
                '.' -> Print()
                ',' -> Write()
                '[' -> OpenLoop()
                ']' -> CloseLoop()
                else -> {
                    lastSym = sym
                    continue@parsing
                }
            }

            // Optimizations for parts like ++++ -> ChangeValue(4)
            if (sym == lastSym && lastSym in "><+-") {
                when(sym) {
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

        return tokens
    }
}
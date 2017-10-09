import java.util.LinkedList


class Parser(private val toParse: String) {
    // TODO write code validator
    var tokens: LinkedList<Token> = LinkedList()
        get() {
            return loopParse(optimize(parse(toParse)))
            // Invoking optimize first, because after optimization applied
            // tokens will be shifted
        }

    fun parse(code: String): LinkedList<Token> {
        return code.asSequence().filter {
            it in "-+><.,[]" // Ignoring all symbols except brainfuck instructions
        }.mapNotNullTo(LinkedList()) {
            when (it) {
                '-' -> ChangeValue(-1)
                '+' -> ChangeValue(1)
                '>' -> Move(1)
                '<' -> Move(-1)
                '.' -> Print()
                ',' -> Write()
                '[' -> OpenLoop()
                ']' -> CloseLoop()
                else -> null
            }
        }
    }

    // This function merges row of same tokens in one
    // e.g. Move(1), Move(-1), Move(-1) will merge in Move(1 + (-1) + (-1))
    fun optimize(tkns: LinkedList<Token>): LinkedList<Token> {
        return tkns.fold(LinkedList(), { acc, elem ->
            val token = acc.pop().merge(elem) ?: elem
            acc.add(token)
            return acc
        })
    }

    fun loopParse(tkns: LinkedList<Token>): LinkedList<Token> {
        // TODO this too -_-
        return tkns
    }

}
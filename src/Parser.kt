import java.util.LinkedList


class Parser(private val toParse: String) {
    // TODO write code validator
    var tokens: LinkedList<Token> = LinkedList()
        get() {
            return loopParse(optimize(parse(toParse)))
            // Invoking optimize first, because after optimization applied
            // tokens will be shifted
        }

    fun parse(code: String): LinkedList<Token> = code.mapNotNullTo(LinkedList()) {
        when (it) {
            '-' -> ChangeValue(-1)
            '+' -> ChangeValue(1)
            '>' -> Move(1)
            '<' -> Move(-1)
            '.' -> Print
            ',' -> Write
            '[' -> OpenLoop()
            ']' -> CloseLoop()
            else -> null
        }
    }

    // This function merges row of same tokens in one
    // e.g. Move(1), Move(-1), Move(-1) will merge in Move(1 + (-1) + (-1))
    fun optimize(tkns: LinkedList<Token>): LinkedList<Token> {
        val res: LinkedList<Token> = LinkedList() // Getting rid of
        res.add(tkns.removeFirst()) // if check for empty acc
        return tkns.fold(res) { acc, elem ->
            val merged = acc.last.merge(elem)
            if (merged != null) {
                acc[acc.lastIndex] = merged
            } else {
                acc.add(elem)
            }
            acc
        }
    }

    fun loopParse(tkns: LinkedList<Token>): LinkedList<Token> {
        val temp = tkns.filterIndexedTo(LinkedList()) { index, token ->
            token.index = index
            if (token is OpenLoop || token is CloseLoop) {
                true
            }
            false
        }
        for (x in 1..(temp.size / 2)) { // We always have even number of brackets(because of validator)
            val first = temp.removeFirst() as OpenLoop // always OpenLoop
            val last = temp.pop() as CloseLoop // always CloseLoop
            first.closeLoopIndex = last.index
            last.openLoopIndex = first.index
        }
        return tkns
    }

}
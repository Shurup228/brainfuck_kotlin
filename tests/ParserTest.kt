import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.LinkedList


internal class ParserTest {
    @Test
    fun parse() {
        val code = "< > + - [ ] , ."
        val parser = Parser(code)
        val tokens = parser.parse(code)
        val expectedTokens = arrayOf(
                Move(-1), Move(1), ChangeValue(1),
                ChangeValue(-1), OpenLoop(),
                CloseLoop(), Write, Print
        ).toCollection(LinkedList())
        assertEquals(tokens, expectedTokens)
    }

    @Test
    fun optimize() {
        val code = "++---.>>>"
        val parser = Parser(code)
        val tokens = parser.optimize(parser.parse(code))
        val expectedTokens = arrayOf(
                ChangeValue(-1), Print, Move(3)
        ).toCollection(LinkedList())
        assertEquals(expectedTokens, tokens)
    }

    @Test
    fun loopParse() {
    }

}
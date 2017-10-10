import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.LinkedList


internal class ParserTest {
    val parser = Parser("kek")
    @Test
    fun parse() {
        val code = "< > + - [ ] , ."
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
        val tokens = parser.optimize(parser.parse(code))
        val expectedTokens = arrayOf(
                ChangeValue(-1), Print, Move(3)
        ).toCollection(LinkedList())
        assertEquals(expectedTokens, tokens)
    }

    @Test
    fun loopParse() {
        val code = "+[--[-]++]"
        val tokens = parser.loopParse(parser.optimize(parser.parse(code)))
        val expectedTokens = arrayOf(
                ChangeValue(1), OpenLoop(1, 7),
                ChangeValue(-2), OpenLoop(3, 5),
                ChangeValue(-1), CloseLoop(5, 3),
                ChangeValue(2), CloseLoop(7, 1)
        ).toCollection(LinkedList())
        assertEquals(expectedTokens, tokens)
    }

}
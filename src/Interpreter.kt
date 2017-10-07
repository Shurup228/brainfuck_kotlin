import java.util.LinkedList

class Interpreter(private val code: String) {
    private val field: Field = Field()
    private val tape: LinkedList<Token> = Parser(code).parse()

    fun loop() {}

    fun run() {
        while (true) {

        }
    }
}
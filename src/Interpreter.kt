import java.util.LinkedList
import java.util.Scanner

class Interpreter(code: String) {
    private val field: Field = Field()
    private val instructions: LinkedList<Token> = Parser(code).tokens
    private var index: Int = 0
    private val scanner: Scanner = Scanner(System.`in`)

    fun run(start: Int = 0, end: Int = instructions.size) {
        index = start
        while (index < end) {
            val instruction = instructions[index]
            when (instruction) {
                is Move -> field.move(instruction.value)
                is ChangeValue -> field.change(instruction.value)
                is Print -> print(field.get())
                is Write -> field.set(scanner.next()[0])
                is OpenLoop -> {
                    if (field.get().toInt() == 0) {
                        index = instruction.closeLoopIndex
                    } else {
                        run(index + 1, instruction.closeLoopIndex)
                    }
                }
                is CloseLoop -> {
                    if (field.get().toInt() != 0) {
                        index = instruction.openLoopIndex - 1
                    }
                }
            }
            index += 1
        }
    }
}
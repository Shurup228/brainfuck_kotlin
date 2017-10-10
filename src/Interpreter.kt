import java.util.LinkedList
import java.util.Scanner

class Interpreter(code: String) {
    private val field: Field = Field()
    private val instructions: LinkedList<Token> = Parser(code).tokens
    private var index: Int = 0
    private val scanner: Scanner = Scanner(System.`in`)

    fun run(start: Int = 0, end: Int = instructions.lastIndex) {
        index = start
        while (index <= end) {
            val instruction = instructions[index]
            when (instruction) {
                is Move -> field.move(instruction.value)
                is ChangeValue -> field.change(instruction.value)
                is Print -> print(field.current)
                is Write -> field.current = scanner.next()[0]
                is OpenLoop -> {
                    if (field.current.toInt() == 0) {
                        index = instruction.closeLoopIndex
                    } else {
                        run(index + 1, instruction.closeLoopIndex - 1) // if we don't substract 1
                        index -= 1 // from that value, run will go into recursion and then add to index
                    } // number of loops it encounter and we need to substract from index, so that
                } // it won't pass one instruction right after the CloseLoop token
                is CloseLoop -> {
                    if (field.current.toInt() != 0) {
                        index = instruction.openLoopIndex - 1
                    }
                }
            }
            index += 1
        }
    }
}
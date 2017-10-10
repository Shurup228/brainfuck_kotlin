import java.io.File


fun main(args: Array<String>) {
    if (args[0] in "--help") {
        println("Usage:\n\t-h, --help - print this message\n\tfile - file with brainfuck programm")
        return
    }

    val prog = File(args[0]).readText()
    Interpreter(prog).run()

}
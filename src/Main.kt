import java.io.File
import java.util.Scanner


fun main(args: Array<String>) {
    val progsDir = File("progs")
    val progs: MutableMap<String, File> = mutableMapOf()
    val scanner = Scanner(System.`in`)

    for (prog in progsDir.listFiles()) {
        progs[prog.name.split(".")[0]] = prog
    }

    // List available programs
    println("Choose program to run:")
    for ((k, _) in progs) {
        println("\t$k")
    }
    val choose = scanner.next()
    progs[choose]?.readText()?.let {
        Interpreter(it).run()
    }

}

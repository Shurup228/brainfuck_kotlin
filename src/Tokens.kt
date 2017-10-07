/*
Tokens represents code units from brainfuck e.g.
Move -> >, <
Print -> .
Write -> ,
OpenLoop -> [
CloseLoop -> ]
ChangeValue -> +, -

Tokens implemented in order(and only) to use benefits of type system of kotlin.
*/
abstract class Token

class Move(var value: Int) : Token() // value -> how many cells to skip

class Print : Token()

class Write : Token()

class OpenLoop : Token()

class CloseLoop : Token()

class ChangeValue(var value: Int) : Token() // value -> how much to change the value of current cell


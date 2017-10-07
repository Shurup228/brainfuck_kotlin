class Field() {
    private val arr: CharArray = CharArray(3000)
    private var counter: Int = 0

    fun next(i: Int = 1) {
        counter += i
    }

    fun prev(i: Int = 1) {
        counter -= i
    }

    fun change(i: Int) {
        arr[counter] = (arr[counter] + i)
    }

    fun get(): Char {
        return arr[counter]
    }

    fun set(value: Char) {
        arr[counter] = value
    }
}
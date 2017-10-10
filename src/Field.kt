class Field {
    private val arr: CharArray = CharArray(3000)
    var counter: Int = 0

    fun move(i: Int) {
        counter += i
        if (counter < 0)
            counter = 0
        else if (counter > arr.size)
            counter = arr.size
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

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class FieldTest {
    val field = Field()
    @Test
    fun move() {
        field.move(2)
        assertEquals(2, field.counter)
        field.move(-3)
        assertEquals(0, field.counter)
    }

    @Test
    fun change() {
        field.change(20)
        assertEquals(20, field.get().toInt())
        field.change(-25)
        assertEquals(65531, field.get().toInt())
    }
}
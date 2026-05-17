package elements

import exceptions.InvalidElementValueException
import kotlinx.serialization.Serializable

/**
 * Человек для города [City].
 *
 * @param name
 * @param age
 * @param height
 */
@Serializable
class Human(private val name: String, private val age: Long, private val height: Float) {
    init{
        if (name == "") throw InvalidElementValueException(name)
        if (age <= 0) throw InvalidElementValueException(age)
        if (height <= 0) throw InvalidElementValueException(height)
    }

    override fun toString(): String {
        return "$name, возраст $age, рост $height"
    }
}
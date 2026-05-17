package elements

import exceptions.InvalidElementValueException
import kotlinx.serialization.Serializable

/**
 * Координаты для города [City].
 *
 * @param x Координата по оси X типа [Int] больше -827.
 * @param y Координата по оси Y типа [Double].
 *
 * @throws InvalidElementValueException В случае, если координаты не соответствуют необходимым требованиям.
 *
 * @constructor Принимает все указанные выше параметры, создавая готовый к использованию
 *              объект с заданными координатами.
 *
 * @since 1.0
 */
@Serializable
class Coordinates(private val x: Int, private val y: Double) {
    init{
        if(x <= -827) throw InvalidElementValueException(x)
    }

    override fun toString(): String {
        return "x = $x, y = $y"
    }
}
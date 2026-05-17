package exceptions

/**
 * Исключение, показывающее, что данное значение элемента не может быть установлено.
 *
 * Наследуется от класса [Exception].
 *
 * @param value Полученное значение.
 *
 * @property message Сообщение об ошибке типа [String].
 *
 * @constructor Принимает все перечисленные выше параметры.
 *
 * @since 1.0
 */
class InvalidElementValueException(private val value: Any): Exception() {
    override val message: String = "Значение '$value' не может быть задано"
}
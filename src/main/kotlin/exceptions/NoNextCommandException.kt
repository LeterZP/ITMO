package exceptions

/**
 * Исключение, показывающее, что очередь на выполнение команд пуста.
 *
 * Наследуется от класса [Exception].
 *
 * @property message Сообщение об ошибке типа [String].
 *
 * @constructor Стандартный конструктор класса [Exception].
 *
 * @since 1.0
 */
class NoNextCommandException: Exception() {
    override val message: String = "Чтение скрипта окончено"
}
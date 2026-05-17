package exceptions

/**
 * Исключение, показывающее, что программа завершила работу.
 *
 * Наследуется от класса [Exception].
 *
 * @property message Сообщение об ошибке типа [String].
 *
 * @constructor Стандартный конструктор класса [Exception].
 *
 * @since 1.0
 */
class ProgramExitException(): Exception() {
    override val message: String = "Завершение работы."
}
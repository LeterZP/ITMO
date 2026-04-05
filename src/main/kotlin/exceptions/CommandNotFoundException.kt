package exceptions

/**
 * Исключение, показывающее, что исполняемая команда не найдена.
 *
 * Наследуется от класса [Exception].
 *
 * @param command Выполняемая [command] типа [Command][commands.Command].
 *
 * @property message Сообщение об ошибке типа [String].
 *
 * @constructor Принимает все перечисленные выше параметры.
 *
 * @since 1.0
 */
class CommandNotFoundException(private val command: String): Exception() {
    override val message: String = "Команда $command не найдена"
}
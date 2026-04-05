package exceptions

import commands.Command

/**
 * Исключение, показывающее, что количество полученных командой аргументов отличается от ожидаемого.
 *
 * Наследуется от класса [Exception].
 *
 * @param command Выполняемая [Command][commands.Command].
 * @param amount Количество полученных аргументов типа [Int].
 *
 * @property message Сообщение об ошибке типа [String].
 *
 * @constructor Принимает все перечисленные выше параметры.
 *
 * @since 1.0
 */
class InvalidAmountOfArgumentsException(private val command: Command, private val amount: Int): Exception() {
    private val name: String = command.getName()
    private val realAmount: Int = command.tokenAmount

    override val message: String = "Команда $name принимает только $realAmount аргументов, а не $amount"
}
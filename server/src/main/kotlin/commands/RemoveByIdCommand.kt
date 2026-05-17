package commands

import core.CommandInvoker

/**
 * Команда для удаления элемента по id.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class RemoveByIdCommand(override val ci: CommandInvoker): Command(ci) {
    override val argumentsAmount: Int = 1

    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        var value: Long
        try {
            value = arguments[0].toLong()
            ci.cm.removeElement(value)
            result = "Элемент $value успешно удалён.\n"
            ci.io.logger.info("Элемент $value удалён.")
        }
        catch (e: NumberFormatException) {
            result = "${arguments[0]} не является id элемента.\n"
            ci.io.logger.warning("Неверный id элемента.")
        }
    }

    override fun describe(): String {
        return "Удаляет элемент по его id"
    }

    override fun getSyntax(): String {
        return "[id]"
    }

    override fun getName(): String {
        return "remove_by_id"
    }
}
package commands

import core.CommandInvoker

/**
 * Команда для удаления элементов с [id][elements.City.id] выше заданного.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class RemoveGreaterCommand(override val ci: CommandInvoker): Command(ci) {
    override val argumentsAmount: Int = 1

    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        try {
            val count: Int = ci.cm.removeGreater(arguments[0].toLong())
            result = "Удалено $count элементов.\n"
            ci.io.logger.info("Удалено $count элементов.")
        } catch (e: NumberFormatException) {
            result = "${arguments[0]} не является id элемента.\n"
            ci.io.logger.warning("Неверный id элемента.")
        }
    }

    override fun describe(): String {
        return "Удаляет элементы коллекции больше заданного"
    }

    override fun getSyntax(): String {
        return "[id]"
    }

    override fun getName(): String {
        return "remove_greater"
    }
}
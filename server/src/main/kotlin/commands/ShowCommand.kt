package commands

import core.CommandInvoker

/**
 * Команда для получения информации об элементах коллекции.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class ShowCommand(override val ci: CommandInvoker): Command(ci) {
    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        val output: String = ci.cm.getAllElementsToString()
        if (output != "") result = output + "\n"
        else result = "Коллекция пуста.\n"
        ci.io.logger.info("Список элементов найден.")
    }

    override fun describe(): String {
        return "Выводит список всех элементов коллекции"
    }

    override fun getName(): String {
        return "show"
    }
}
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
class ShowCommand(ci: CommandInvoker): Command(ci) {
    override fun execute(token: List<String>) {
        super.execute(token)
        val output: String = ci.cm.getAllElementsToString()
        if (output != "") ci.io.write(output + "\n")
        else ci.io.write("Коллекция пуста.\n")
    }

    override fun describe(): String {
        return "Выводит список всех элементов коллекции"
    }

    override fun getName(): String {
        return "show"
    }
}
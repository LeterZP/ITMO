package commands

import core.CommandInvoker

/**
 * Команда для переворота коллекции.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class ReorderCommand(ci: CommandInvoker): Command(ci) {
    override fun execute(token: List<String>) {
        super.execute(token)
        ci.cm.reorderElements()
        ci.io.write("Коллекция успешно перевёрнута.\n")
    }

    override fun describe(): String {
        return "Переворачивает коллекцию"
    }

    override fun getName(): String {
        return "reorder"
    }
}
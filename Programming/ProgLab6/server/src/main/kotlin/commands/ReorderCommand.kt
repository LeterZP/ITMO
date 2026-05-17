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
class ReorderCommand(override val ci: CommandInvoker): Command(ci) {
    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        ci.cm.reorderElements()
        result = "Коллекция успешно перевёрнута.\n"
        ci.io.logger.info("Коллекция перевернута.")
    }

    override fun describe(): String {
        return "Переворачивает коллекцию"
    }

    override fun getName(): String {
        return "reorder"
    }
}
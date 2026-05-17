package commands

import core.CommandInvoker

/**
 * Команда для очистки коллекции.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class ClearCommand(override val ci: CommandInvoker): Command(ci) {
    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        ci.cm.clearCollection()
        result = "Коллекция отчищена.\n"
        ci.io.logger.info("Коллекция отчищена.")
    }

    override fun describe(): String {
        return "Удаляет все элементы коллекции"
    }

    override fun getName(): String {
        return "clear"
    }
}
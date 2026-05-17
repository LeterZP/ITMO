package commands

import core.CommandInvoker

/**
 * Команда для получения информации о коллекции.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class InfoCommand(override val ci: CommandInvoker): Command(ci) {
    override fun describe(): String {
        return "Выводит всю информацию о коллекции"
    }

    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        val time = ci.cm.initializationTime
        val size = ci.cm.size()
        result = "Информация о коллекции:\n"
        result += "  --Тип коллекции: java.util.Stack\n"
        result += "  --Дата инициализации коллекции: $time\n"
        result += "  --Количество элементов в коллекции: $size\n"
        ci.io.logger.info("Найдена информация о коллекции.")
    }

    override fun getName(): String {
        return "info"
    }
}
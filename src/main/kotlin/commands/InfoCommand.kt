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
class InfoCommand(ci: CommandInvoker): Command(ci) {
    override fun describe(): String {
        return "Выводит всю информацию о коллекции"
    }

    override fun execute(token: List<String>) {
        super.execute(token)
        val time = ci.cm.initializationTime
        val size = ci.cm.size()
        ci.io.write("Информация о коллекции:" + "\n")
        ci.io.write("  --Тип коллекции: java.util.Stack" + "\n")
        ci.io.write("  --Дата инициализации коллекции: $time\n")
        ci.io.write("  --Количество элементов в коллекции: $size\n")
    }

    override fun getName(): String {
        return "info"
    }
}
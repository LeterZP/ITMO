package commands

import core.CommandInvoker
import java.io.IOException

/**
 * Команда для сохранения коллекции в файл.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class SaveCommand(override val ci: CommandInvoker): Command(ci) {
    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        try {
            ci.cm.saveToFile()
            result = "Коллекция успешно сохранена.\n"
            ci.io.logger.info("Коллекция сохранена.")
        } catch (e: IOException) {
            result = "Файл сохранения не найден. Коллекция не сохранена.\n"
            ci.io.logger.warning("Файл сохранения не найден.")
        }
    }

    override fun describe(): String {
        return "Сохраняет коллекцию в файл"
    }

    override fun getName(): String {
        return "save"
    }
}
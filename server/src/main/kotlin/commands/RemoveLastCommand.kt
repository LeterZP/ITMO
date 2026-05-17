package commands

import core.CommandInvoker
import exceptions.CollectionHasNoElementException

/**
 * Команда для удаления последнего элемента коллекции.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class RemoveLastCommand(override val ci: CommandInvoker): Command(ci) {
    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        try {
            ci.cm.removeLast()
            result = "Элемент успешно удалён.\n"
            ci.io.logger.info("Последний элемент удалён.")
        } catch (e: CollectionHasNoElementException) {
            result = "Последний элемент не найден: коллекция пуста.\n"
            ci.io.logger.warning("Коллекция пуста.")
        }
    }

    override fun describe(): String {
        return "Удаляет последний элемент коллекции"
    }

    override fun getName(): String {
        return "remove_last"
    }
}
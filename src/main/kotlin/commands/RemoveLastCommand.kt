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
class RemoveLastCommand(ci: CommandInvoker): Command(ci) {
    override fun execute(token: List<String>) {
        super.execute(token)
        try {
            ci.cm.removeLast()
            ci.io.write("Элемент успешно удалён.\n")
        } catch (e: CollectionHasNoElementException) {
            ci.io.write("Последний элемент не найден: коллекция пуста.\n")
        }
    }

    override fun describe(): String {
        return "Удаляет последний элемент коллекции"
    }

    override fun getName(): String {
        return "remove_last"
    }
}
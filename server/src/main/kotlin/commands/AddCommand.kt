package commands

import core.CommandInvoker
import elements.CityBuilder
import exceptions.InvalidAmountOfArgumentsException
import exceptions.InvalidElementValueException

/**
 * Команда для добавления элемента в коллекцию.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class AddCommand(override val ci: CommandInvoker): Command(ci) {
    override val argumentsAmount = CityBuilder().size

    override fun execute(arguments: List<String>) {
        val creator = CityBuilder()
        if (arguments.size != creator.size) throw InvalidAmountOfArgumentsException(this, arguments.size)
        var count = 0
        try {
            while (true) {
                creator.setField(arguments[count], count)
                if (count == creator.size-1) break
                count++
            }
            ci.cm.addElement(creator.create())
            ci.io.logger.info("Элемент успешно добавлен.")
            result = "Элемент успешно добавлен.\n"
        } catch (e: InvalidElementValueException) {
            result = e.message + "\n"
            ci.io.logger.warning(e.message)
        }
    }

    override fun getSyntax(): String {
        return "{element}"
    }

    override fun getName(): String {
        return "add"
    }

    override fun describe(): String {
        return "Добавляет элемент в коллекцию"
    }
}
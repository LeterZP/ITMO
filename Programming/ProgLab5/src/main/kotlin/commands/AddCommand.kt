package commands

import core.CommandInvoker
import elements.CityBuilder
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
class AddCommand(ci: CommandInvoker): Command(ci) {
    override fun execute(token: List<String>) {
        super.execute(token)
        val creator: CityBuilder = CityBuilder()
        var count: Int = 0
        while (true) {
            val value: String = ci.nextValue(creator.getField(count))
            try {
                creator.setField(value, count)
            } catch (e: InvalidElementValueException) {
                ci.io.write(e.message + "\n")
                continue
            }
            if (count == creator.size-1) break
            count++
        }
        try {
            ci.cm.addElement(creator.create())
            ci.io.write("Элемент успешно добавлен." + "\n")
        } catch (e: InvalidElementValueException) {
            ci.io.write(e.message + "\n")
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
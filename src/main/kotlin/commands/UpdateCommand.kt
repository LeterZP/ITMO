package commands

import core.CommandInvoker
import elements.CityBuilder
import exceptions.InvalidElementValueException

/**
 * Команда для обновления элемента коллекции.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class UpdateCommand(ci: CommandInvoker): Command(ci) {
    override val tokenAmount: Int = 1

    override fun execute(token: List<String>) {
        super.execute(token)
        val id: Long
        try {
            id = token[0].toLong()
            val creator: CityBuilder = CityBuilder()
            var count: Int = 0
            while (true) {
                val value: String = ci.nextValue(creator.getField(count))
                try {
                    if (value != "") creator.setField(value, count)
                    if (count == creator.size-1) break
                    count++
                } catch (_: InvalidElementValueException) {
                    ci.io.write("Значение $value не может быть установлено. Повторите ввод.\n")
                }
            }
            creator.update(ci.cm.getElement(id))
            ci.io.write("Элемент успешно обновлён.\n")
        }
        catch (_: NumberFormatException) { ci.io.write("${token[0]} не является id элемента.\n") }
        catch (e: InvalidElementValueException) { ci.io.write(e.message + "\n") }
    }

    override fun describe(): String {
        return "Обновляет значение элемента"
    }

    override fun getSyntax(): String {
        return "[id] {element}"
    }

    override fun getName(): String {
        return "update"
    }
}
package commands

import core.CommandInvoker
import elements.Government

/**
 * Команда для получения всех свойств вида [Government] в сортированном виде.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class PrintFieldAscendingGovernmentCommand(ci: CommandInvoker): Command(ci) {
    override fun execute(token: List<String>) {
        super.execute(token)
        val governments: MutableList<Government?> = ci.cm.getSortedGovernments()
        for (element in governments) {
            ci.io.write(element.toString() + "\n")
        }
    }

    override fun describe(): String {
        return "Выводит правительства городов в порядке возрастания"
    }

    override fun getName(): String {
        return "print_field_ascending_government"
    }
}
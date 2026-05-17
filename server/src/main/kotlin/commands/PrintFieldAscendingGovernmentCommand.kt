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
class PrintFieldAscendingGovernmentCommand(override val ci: CommandInvoker): Command(ci) {
    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        val governments: List<Government?> = ci.cm.getSortedGovernments()
        result = ""
        for (element in governments) {
            result += element.toString()+ "\n"
        }
        ci.io.logger.info("Найдены все правительства.")
    }

    override fun describe(): String {
        return "Выводит правительства городов в порядке возрастания"
    }

    override fun getName(): String {
        return "print_field_ascending_government"
    }
}
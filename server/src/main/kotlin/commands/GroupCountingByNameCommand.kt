package commands

import core.CommandInvoker

/**
 * Команда для группировки по имени с подсчётом повторений.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class GroupCountingByNameCommand(override val ci: CommandInvoker): Command(ci) {
    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        val names: HashMap<String, Int> = ci.cm.groupElements()
        result = "Названия городов:\n"
        for (name in names) {
            result += "  --${name.key}: ${name.value}\n"
        }
        ci.io.logger.info("Найден список всех элементов.")
    }

    override fun describe(): String {
        return "Выводит количество элементов, сгруппированных по названиям"
    }

    override fun getName(): String {
        return "group_counting_by_name"
    }
}
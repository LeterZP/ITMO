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
class GroupCountingByNameCommand(ci: CommandInvoker): Command(ci) {
    override fun execute(token: List<String>) {
        super.execute(token)
        val names: HashMap<String, Int> = ci.cm.groupElements()
        ci.io.write("Названия городов:" + "\n")
        for (name in names) {
            ci.io.write("  --" + name.key + ": " + name.value + "\n")
        }
    }

    override fun describe(): String {
        return "Выводит количество элементов, сгруппированных по названиям"
    }

    override fun getName(): String {
        return "group_counting_by_name"
    }
}
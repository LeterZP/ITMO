package commands

import core.CommandInvoker

/**
 * Команда для исполнения скрипта.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class ExecuteScriptCommand(override val ci: CommandInvoker): Command(ci) {
    override val argumentsAmount: Int = 1

    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        if (arguments[0] !in ci.executionHistory) {
            ci.executionHistory.add(arguments[0])
            val previousSource = ci.io.source
            ci.io.source = arguments[0]
            ci.addNext(ci.io.read())
            ci.io.source = previousSource
        } else {
            result = "Обнаружена бесконечная рекурсия. Отказ в запуске скрипта ${arguments[0]}.\n"
        }
    }

    override fun describe(): String {
        return "Запускает команды из скрипта"
    }

    override fun getSyntax(): String {
        return "[file_name]"
    }

    override fun getName(): String {
        return "execute_script"
    }
}
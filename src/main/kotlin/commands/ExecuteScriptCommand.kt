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
class ExecuteScriptCommand(ci: CommandInvoker): Command(ci) {
    override val tokenAmount: Int = 1

    override fun execute(token: List<String>) {
        super.execute(token)
        if (token[0] !in ci.executionHistory) {
            ci.executionHistory.add(token[0])
            val previousSource = ci.io.source
            ci.io.source = token[0]
            ci.addNext(ci.io.read())
            ci.io.source = previousSource
        } else {
            ci.io.write("Обнаружена бесконечная рекурсия. Отказ в запуске скрипта ${token[0]}.\n")
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
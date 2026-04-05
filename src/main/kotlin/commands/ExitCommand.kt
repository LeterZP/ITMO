package commands

import core.CommandInvoker
import exceptions.ProgramExitException

/**
 * Команда для выхода из программы.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @throws ProgramExitException В качестве сигнала завершения программы.
 *
 * @since 1.0
 */
class ExitCommand(ci: CommandInvoker): Command(ci) {
    override fun execute(token: List<String>) {
        super.execute(token)
        throw ProgramExitException()
    }

    override fun describe(): String {
        return "Завершает работу программы"
    }

    override fun getName(): String {
        return "exit"
    }
}
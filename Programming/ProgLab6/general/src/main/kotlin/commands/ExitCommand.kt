package commands

import core.CommandInvokerInterface
import exceptions.ProgramExitException

/**
 * Команда для выхода из программы.
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @throws ProgramExitException В качестве сигнала завершения программы.
 *
 * @since 1.0
 */
class ExitCommand(ci: CommandInvokerInterface): Command(ci) {
    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        throw ProgramExitException()
    }

    override fun describe(): String {
        return "Завершает работу программы"
    }

    override fun getName(): String {
        return "exit"
    }
}
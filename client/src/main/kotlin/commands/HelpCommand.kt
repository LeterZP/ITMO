package commands

import core.CommandInvoker
import exceptions.CommandNotFoundException
import exceptions.InvalidAmountOfArgumentsException

/**
 * Команда для вывода списка доступных команд.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class HelpCommand(override val ci: CommandInvoker): Command(ci) {
    /**
     * Выдаёт полную информацию о команде.
     *
     * @param command Команда типа [Command].
     *
     * @return Информацию о команде типа [String].
     *
     * @since 1.0
     */
    private fun getFullInfo(command: CommandWrapper): String {
        return "  --" + command.string + " : " + command.describe
    }

    /**
     * Выдаёт краткую информацию о команде.
     *
     * @param command Команда типа [Command].
     *
     * @return Информацию о команде типа [String].
     *
     * @since 1.0
     */
    private fun getInfo(command: CommandWrapper): String {
        return "  --" + command.name + " : " + command.describe
    }

    override fun execute(arguments: List<String>) {
        try {
            if (arguments.isEmpty()) {
                result = "Список доступных команд:" + "\n"
                for (command in ci.commands.values) {
                    result += getInfo(command) + "\n"
                }
            } else if (arguments.size == 1) {
                if (arguments[0] in ci.commands.keys) {
                    result += getFullInfo(ci.commands.get(arguments[0])!!) + "\n"
                } else {
                    throw CommandNotFoundException(arguments[0])
                }
            } else throw InvalidAmountOfArgumentsException(this, arguments.size)
        } catch (e: CommandNotFoundException) {
            result = e.message + "\n"
        }
    }

    override fun getName(): String {
        return "help"
    }

    override fun getSyntax(): String {
        return "[<null> | command]"
    }

    override fun describe(): String {
        return "Выводит информацию о всех командах либо описание одной команды"
    }
}
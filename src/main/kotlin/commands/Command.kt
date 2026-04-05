package commands

import core.CommandInvoker
import exceptions.InvalidAmountOfArgumentsException

/**
 * Абстрактный класс для всех команд.
 *
 * @param ci [CommandInvoker], откуда вызывается команда.
 *
 * @property tokenAmount Количество аргументов, которые принимает команда, типа [Int].
 *
 * @since 1.0
 */
abstract class Command(protected val ci: CommandInvoker) {
    open val tokenAmount: Int = 0

    override fun toString(): String {
        if (getSyntax() == "") return getName() + getSyntax()
        return getName() + " " + getSyntax()
    }

    /**
     * Исполняет команду.
     *
     * @param token [List], содержащий в себе все аргументы команды типа [String].
     *
     * @throws InvalidAmountOfArgumentsException В случае, если количество данных элементов не совпадает с
     *                                           количеством элементов, которые принимает команда.
     *
     * @since 1.0
     */
    open fun execute(token: List<String>) {
        while (token.contains("")) token.minus("")
        if (token.size != tokenAmount) throw InvalidAmountOfArgumentsException(this, token.size)
    }

    /**
     * Описывает команду.
     *
     * @return Описание команды типа [String].
     *
     * @since 1.0
     */
    open fun describe(): String {
        return "У этой команды нет описания"
    }

    /**
     * Описывает синтаксис команды.
     *
     * @return Синтаксис команды типа [String].
     *
     * @since 1.0
     */
    open fun getSyntax(): String {
        return ""
    }

    /**
     * Описывает имя команды.
     *
     * @return Имя команды типа [String].
     *
     * @since 1.0
     */
    open fun getName(): String {
        return "command"
    }
}
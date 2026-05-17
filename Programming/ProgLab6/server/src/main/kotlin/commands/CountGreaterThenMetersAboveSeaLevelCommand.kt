package commands

import core.CommandInvoker

/**
 * Команда для вывода количества элементов коллекции, высота над уровнем моря которых больше заданного.
 *
 * @param ci [CommandInvoker] для [Command].
 *
 * @constructor Вызывает родительский конструктор класса [Command].
 *
 * @since 1.0
 */
class CountGreaterThenMetersAboveSeaLevelCommand(override val ci: CommandInvoker): Command(ci) {
    override val argumentsAmount: Int = 1

    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        try {
            result = "Количество: " + ci.cm.countHigherThen(arguments[0].toLong()) + "\n"
            ci.io.logger.info("Найдено количество элементов выше ${arguments[0]} метров.")
        } catch (e: NumberFormatException) {
            result = "Невозможно сравнить с данным значением, оно должно быть типа Long.\n"
            ci.io.logger.warning("Неверный тип значения ${arguments[0]}.")
        }
    }

    override fun describe(): String {
        return "Выводит количество элементов выше заданного уровня моря"
    }

    override fun getSyntax(): String {
        return "[metersAboveSeaLevel]"
    }

    override fun getName(): String {
        return "count_greater_then_meters_above_sea_level"
    }
}
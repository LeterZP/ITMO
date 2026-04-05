package core

import exceptions.*
import io.IOManager
import java.io.IOException

/**
 * Класс интерактивного взаимодействия с программой.
 *
 * Позволяет консольно взаимодействовать с программой и вводить команды через консоль.
 *
 * @param cm [CollectionManager], содержащий коллекцию элементов [City][elements.City], с которой будут взаимодействовать команды.
 *
 * @constructor Принимает все описанные выше параметры.
 *
 * @since 1.0
 */
class InteractiveMode(
    private val cm: CollectionManager,
    private val io: IOManager
) {
    private val ci: CommandInvoker = CommandInvoker(io, cm)

    /**
     * Запускает взаимодействие с программой.
     *
     * @since 1.0
     */
    private fun interaction() {
        var isWorking: Boolean = true
        while (isWorking) {
            try {
                io.write("=> ")
                ci.readCommand()
            } catch (e: ProgramExitException) {
                io.write(e.message + "\n")
                isWorking = false
            } catch (e: IOException) {
                io.source = null
                io.write("Ошибка чтения файла или записи в него.\n")
            } catch (e: Exception) {
                io.write("Возникла непредвиденная ошибка: " + e.message + "\n")
                io.write("Экстренное завершение работы.\n")
                isWorking = false
            }
        }
    }

    /**
     * Запускает интерактивный режим.
     *
     * @since 1.0
     */
    fun start() {
        io.write("Программа запущена в интерактивном режиме. Чтобы увидеть список команд, введите help.\n")
        interaction()
    }
}
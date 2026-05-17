package io

import java.io.IOException

/**
 * Класс для управления чтения и записи из файлов и консоли.
 *
 * @property source Путь к файлу, из которого читаются данные типа [String], по умолчанию null(консоль).
 *
 * @constructor Создаёт объект для взаимодействия с консолью.
 *
 * @since 1.0
 */
class IOManager() {
    var source: String? = null
        set(value) {
            sourceType = if (value == "" || value == null) {
                IOType.CONSOLE
            } else {
                IOType.FILE
            }
            if (sourceType == IOType.FILE) { io = FileIO(value!!) }
            field = value
        }
    private var sourceType: IOType = IOType.CONSOLE
    private lateinit var io: FileIO

    /**
     * Читает данные из [source].
     *
     * @return Строка из [source] типа [String].
     *
     * @throws [IOException] В случае, если данные не могут быть корректно прочитаны.
     *
     * @since 1.0
     */
    fun read(): String {
        val input: String = when (sourceType) {
            IOType.CONSOLE -> readln()
            IOType.FILE -> {
                io.readFile()
            }
        }
        return input
    }

    /**
     * Записывает данные в [source].
     *
     * @param output Данные для записи типа [String].
     *
     * @throws [IOException] В случае, если данные не могут быть корректно записаны.
     *
     * @since 1.0
     */
    fun write(output: String) {
        when (sourceType) {
            IOType.CONSOLE -> print(output)
            IOType.FILE -> {
                val io: FileIO = FileIO(source!!)
                io.writeToFile(output)
            }
        }
    }

    /**
     * Запрашивает ввод значения.
     *
     * @param value Строковое описание необходимого значения типа [String].
     *
     * @return Значение типа [String].
     *
     * @throws [IOException] В случае, если возникли проблемы с чтением/записью данных.
     *
     * @since 1.0
     */
    fun askForValue(value: String): String {
        val output = "Введите $value: "
        write(output)
        val text: String = read()
        return text
    }
}
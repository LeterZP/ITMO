package io

import elements.City
import java.io.IOException
import java.util.Stack

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
            } else if (value.endsWith(".json")) {
                IOType.JSON
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
            IOType.JSON -> throw IOException()
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
            IOType.JSON -> throw IOException()
            IOType.FILE -> {
                val io: FileIO = FileIO(source!!)
                io.writeToFile(output)
            }
        }
    }

    /**
     * Читает данные из файла типа Json и декодирует их.
     *
     * @return Коллекция [Stack] с элементами [City].
     *
     * @throws [IOException] В случае, если данные не могут быть корректно прочитаны.
     * @throws [kotlinx.serialization.SerializationException] В случае ошибки сериализации.
     *
     * @since 1.0
     */
    fun readAsJsonFile(): Stack<City> {
        if (sourceType == IOType.JSON) {
            val io: JsonFileIO = JsonFileIO(source!!)
            val stack: Stack<City> = io.readFromFile()
            return stack
        } else {
            throw IOException()
        }
    }

    /**
     * Записывает данные в файл типа Json, предварительно декодируя их.
     *
     * @param stack Коллекция [Stack] с элементами [City].
     *
     * @throws [IOException] В случае, если данные не могут быть корректно записаны.
     * @throws [kotlinx.serialization.SerializationException] В случае ошибки сериализации.
     *
     * @since 1.0
     */
    fun writeToJsonFile(stack: Stack<City>) {
        if (sourceType == IOType.JSON) {
            val io: JsonFileIO = JsonFileIO(source!!)
            io.writeToFile(stack)
        } else {
            throw IOException()
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
        val output: String = "Введите $value: "
        write(output)
        val text: String = read()
        return text
    }
}
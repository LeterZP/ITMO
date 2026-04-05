package io

import elements.City
import java.util.Stack
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlinx.serialization.json.Json.Default.encodeToString

/**
 * Класс для чтения сериализованных в виде Json элементов коллекции типа [City].
 *
 * @param file Файл с элементами типа [String].
 *
 * @constructor Создаёт готовый к использованию объект, принимая все указанные выше параметры.
 *
 * @since 1.0
 */
class JsonFileIO(private val file: String){
    private val io: FileIO = FileIO(file)

    /**
     * Читает коллекцию из файла.
     *
     * @return [Stack] с элементами [City].
     *
     * @throws [kotlinx.serialization.SerializationException] В случае ошибки сериализации.
     *
     * @since 1.0
     */
    fun readFromFile(): Stack<City> {
        val text: String = io.readFile()
        val decodedStack: Stack<City> = Stack<City>()
        if (text != "") {
            val decodedList: List<City> = decodeFromString<List<City>>(text)
            for (element in decodedList) {
                decodedStack.push(element)
            }
        }
        return decodedStack
    }

    /**
     * Записывает коллекцию в файл.
     *
     * @param stack [Stack] с элементами [City].
     *
     * @throws [kotlinx.serialization.SerializationException] В случае ошибки сериализации.
     *
     * @since 1.0
     */
    fun writeToFile(stack: Stack<City>) {
        val listToEncode: List<City> = stack.toList()
        val text: String = encodeToString(listToEncode)
        io.writeToFile(text)
    }
}
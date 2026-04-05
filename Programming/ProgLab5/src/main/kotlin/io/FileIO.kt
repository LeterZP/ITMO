package io

import java.io.BufferedInputStream
import java.io.BufferedWriter
import java.io.FileInputStream
import java.io.FileWriter

/**
 * Класс для чтения данных из файла.
 *
 * @param file Имя файла типа [String].
 *
 * @constructor Создаёт готовый к использованию объект, принимая все описанные выше параметры.
 *
 * @since 1.0
 */
class FileIO(private val file: String) {

    /**
     * Читает весь файл.
     *
     * @return [String] строку файла.
     *
     * @throws [NoSuchElementException] В случае, если в файле не осталось непрочитанных строк.
     * @throws [java.io.IOException] В случае, если файла не существует.
     *
     * @since 1.0
     */
    fun readFile(): String {
        val reader: BufferedInputStream = BufferedInputStream(FileInputStream(file))
        return reader.readAllBytes().decodeToString()
    }

    /**
     * Записывает строки в файл.
     *
     * @param text Строки для записи типа [String].
     *
     * @throws [java.io.IOException] В случае, если файла не существует.
     *
     * @since 1.0
     */
    fun writeToFile(text: String) {
        val writer: BufferedWriter = BufferedWriter(FileWriter(file))
        writer.write(text)
        writer.close()
    }
}
package io

import elements.City
import java.io.BufferedInputStream
import java.io.BufferedWriter
import java.io.FileInputStream
import java.io.FileWriter
import java.util.Stack
import java.util.logging.FileHandler
import java.util.logging.Logger
import java.util.logging.SimpleFormatter
import kotlinx.serialization.json.Json.Default.encodeToString
import kotlinx.serialization.json.Json.Default.decodeFromString

class IOManager {
    val logger: Logger = Logger.getLogger("server")

    init {
        logger.addHandler(FileHandler("logs"))
        logger.handlers[0].formatter = SimpleFormatter()
    }

    fun readJsonFile(file: String): Stack<City> {
        val reader = BufferedInputStream(FileInputStream(file))
        val text = reader.readAllBytes().decodeToString()
        reader.close()
        val decodedStack: Stack<City> = Stack<City>()
        if (text != "") {
            val decodedList: List<City> = decodeFromString<List<City>>(text)
            for (element in decodedList) {
                decodedStack.push(element)
            }
        }
        return decodedStack
    }

    fun writeJsonFile(file: String, stack: Stack<City>) {
        val listToEncode: List<City> = stack.toList()
        val text: String = encodeToString(listToEncode)
        val writer = BufferedWriter(FileWriter(file))
        writer.write(text)
        writer.close()
    }

    fun readLocalCommands(): String {
        return readln()
    }
}
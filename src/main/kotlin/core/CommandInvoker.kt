package core

import commands.*
import exceptions.CommandNotFoundException
import exceptions.InvalidAmountOfArgumentsException
import exceptions.NoNextCommandException
import io.IOManager
import java.util.Stack

/**
 * Класс вызова команд.
 *
 * Позволяет вызывать инициализированные в нём команды для работы с коллекцией посредством [CollectionManager].
 *
 * @param io [IOManager], откуда читаются команды.
 * @param cm [CollectionManager], управляющий коллекцией.
 *
 * @property commands [HashMap] команд, содержащий имя команды типа [String] и саму команду типа [Command].
 *
 * @constructor Принимает все описанные выше параметры, создавая объект, уже содержащий в себе стандартный набор команд.
 *
 * @since 1.0
 */
class CommandInvoker(val io: IOManager, val cm: CollectionManager) {
    val commands: HashMap<String, Command> = HashMap()
    private val nextToken: Stack<String> = Stack<String>()
    val executionHistory = Stack<String>()

    init {
        initializeCommand(HelpCommand(this))
        initializeCommand(InfoCommand(this))
        initializeCommand(ShowCommand(this))
        initializeCommand(AddCommand(this))
        initializeCommand(UpdateCommand(this))
        initializeCommand(RemoveByIdCommand(this))
        initializeCommand(ClearCommand(this))
        initializeCommand(SaveCommand(this))
        initializeCommand(ExecuteScriptCommand(this))
        initializeCommand(ExitCommand(this))
        initializeCommand(RemoveLastCommand(this))
        initializeCommand(RemoveGreaterCommand(this))
        initializeCommand(ReorderCommand(this))
        initializeCommand(GroupCountingByNameCommand(this))
        initializeCommand(CountGreaterThenMetersAboveSeaLevelCommand(this))
        initializeCommand(PrintFieldAscendingGovernmentCommand(this))
    }

    /**
     * Инициализирует команду, добавляя её в список возможных к использованию.
     *
     * @param command Команда для инициализации, типа [Command].
     *
     * @since 1.0
     */
    fun initializeCommand(command: Command) {
        val name: String = command.getName()
        commands[name] = command
    }

    /**
     * Вызывает команду, читая её либо из очереди команд, либо из консоли в случае, если очередь пуста.
     *
     * @since 1.0
     */
    fun readCommand() {
        do {
            try {
                val instruction: List<String> = try {
                    readNext().trim().split(" ")
                } catch (e: NoNextCommandException) {
                    io.read().trim().split(" ")
                }
                if (instruction.size == 1 && instruction[0] == "") return
                commands.get(instruction[0])?.execute(instruction.minus(instruction[0]))
                    ?: throw CommandNotFoundException(instruction[0])
            } catch (e: CommandNotFoundException) {
                io.write(e.message + "\n")
            } catch (e: InvalidAmountOfArgumentsException) {
                io.write(e.message + "\n")
            }
        } while (!nextToken.isEmpty())
    }

    /**
     * Читает первую в очереди команду.
     *
     * @return Команду с аргументами типа [String].
     *
     * @throws NoNextCommandException В случае, если очередь из команд пуста.
     *
     * @since 1.0
     */
    fun readNext(): String {
        val result: String
        if (nextToken.isEmpty()) {
            executionHistory.clear()
            throw NoNextCommandException()
        }
        else {
            result = nextToken.pop()
        }
        return result
    }

    /**
     * Добавляет одну или несколько команд в очередь.
     *
     * @param instructions Одна или несколько команд с аргументами типа [String].
     *
     * @since 1.0
     */
    fun addNext(instructions: String) {
        val values: List<String> = instructions.lines().reversed()
        for (instruction in values) {
            nextToken.push(instruction)
        }
    }

    /**
     * Выбирает следующее значение для ввода.
     *
     * @see [IOManager.askForValue].
     */
    fun nextValue(output: String): String {
        val input: String
        if (nextToken.isEmpty()) {
            input = io.askForValue(output)
        } else {
            input =  nextToken.pop()
        }
        return input
    }
}
package core

import commands.*
import exceptions.CommandNotFoundException

class CommandInvoker(val cm: CollectionManager): CommandInvokerInterface {
    val commands: HashMap<String, Command> = HashMap()
    val io = cm.io

    init {
        initializeCommand(InfoCommand(this))
        initializeCommand(ShowCommand(this))
        initializeCommand(AddCommand(this))
        initializeCommand(UpdateCommand(this))
        initializeCommand(RemoveByIdCommand(this))
        initializeCommand(ClearCommand(this))
        initializeCommand(RemoveLastCommand(this))
        initializeCommand(RemoveGreaterCommand(this))
        initializeCommand(ReorderCommand(this))
        initializeCommand(GroupCountingByNameCommand(this))
        initializeCommand(CountGreaterThenMetersAboveSeaLevelCommand(this))
        initializeCommand(PrintFieldAscendingGovernmentCommand(this))
        initializeCommand(PingCommand(this))
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
        io.logger.info("Команда $name инициализирована.")
    }

    fun runOnServer(command: String) {
        when (command.trim()) {
            "exit" -> {
                runOnServer("save")
                val exit = ExitCommand(this)
                io.logger.info("Выполняется выход.")
                exit.execute(listOf(""))
            }
            "save" -> {
                val save = SaveCommand(this)
                io.logger.info("Выполняется сохранение.")
                save.execute(listOf(""))
            }
            else -> return
        }
    }

    override fun executeCommand(cw: CommandWrapper): CommandWrapper {
        val command = commands[cw.command] ?: throw CommandNotFoundException(cw.command)
        io.logger.info("Выполняется команда ${cw.name}.")
        command.execute(cw.arguments)
        cw.result = command.result
        return cw
    }

    fun getAllCommandsWrapped(): List<CommandWrapper> {
        val list = mutableListOf<CommandWrapper>()
        for (command in commands.values) {
            val wrapper = CommandWrapper()
            wrapper.wrapCommand(command)
            list.add(wrapper)
        }
        io.logger.info("Подготовлен список команд.")
        return list.toList()
    }
}
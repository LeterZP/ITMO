package commands

import core.CommandInvoker

class PingCommand(override val ci: CommandInvoker): Command(ci) {
    override fun describe(): String {
        return "ping"
    }

    override fun execute(arguments: List<String>) {
        super.execute(arguments)
        result = "ping\n"
    }

    override fun getName(): String {
        return "ping"
    }
}
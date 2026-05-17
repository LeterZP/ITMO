package core

import commands.CommandWrapper

interface CommandInvokerInterface {
    fun executeCommand(cw: CommandWrapper): CommandWrapper
}
package core

import exceptions.ProgramExitException
import io.IOManager

class ServerInitializer(val port: Int, val io: IOManager) {
    val cm = CollectionManager(io)
    val ci = CommandInvoker(cm)
    val cr = ConnectionReceiver(ci, port)
    lateinit var server: Thread
    lateinit var client: Thread

    fun initialize() {
        server = Thread {
            var isWorking = true
            while (isWorking) {
                try {
                    ci.runOnServer(io.readLocalCommands())
                } catch (e: ProgramExitException) {
                    isWorking = false
                }
            }
        }
        client = Thread {
            var isWorking = true
            while (isWorking) {
                try {
                    cr.checkConnection()
                } catch (_: ProgramExitException) {
                    isWorking = false
                }
            }
        }
        server.start()
        client.start()
        io.logger.info("Сервер запущен.")
    }

    fun waitForExit() {
        if (!server.isAlive && !client.isAlive) return
        server.join()
        cr.saveInterrupt()
        io.logger.info("Завершение работы.")
    }
}
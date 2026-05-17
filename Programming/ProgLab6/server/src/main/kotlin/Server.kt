import core.ServerInitializer
import io.IOManager

fun main() {
    val port = 8841
    val io = IOManager()
    val si = ServerInitializer(port, io)
    si.initialize()
    si.waitForExit()
}
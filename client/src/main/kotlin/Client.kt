import core.ConnectionManager
import core.InteractiveMode
import exceptions.ProgramExitException
import io.IOManager
import java.net.InetAddress

fun main() {
    val host = InetAddress.getLocalHost()
    val port = 8841
    val io = IOManager()
    val cm = ConnectionManager(io, host, port)
    try {
        val im = InteractiveMode(io, cm)
        im.start()
    } catch (e: ProgramExitException) {
        io.write(e.message+"\n")
    }

}
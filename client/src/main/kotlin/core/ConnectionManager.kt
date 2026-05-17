package core

import commands.CommandWrapper
import exceptions.ConnectionException
import io.IOManager
import java.net.InetSocketAddress
import java.net.InetAddress
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import kotlinx.serialization.json.Json.Default.encodeToString
import kotlinx.serialization.json.Json.Default.decodeFromString

class ConnectionManager(private val io: IOManager, private val host: InetAddress, private val port: Int) {
    fun askCommandList(): List<CommandWrapper> {
        val cw = CommandWrapper()
        cw.name = "help"
        val result = sendAndReceive(cw)
        return decodeFromString(result.result)
    }

    fun sendAndReceive(cw: CommandWrapper): CommandWrapper {
        val address: SocketAddress = InetSocketAddress(host, port)
        val selector = Selector.open()
        val channel = DatagramChannel.open()
        val sendBuffer = ByteBuffer.wrap(encodeToString(cw).toByteArray())
        val bytes = ByteArray(32768)
        val receiveBuffer = ByteBuffer.wrap(bytes)
        var result = CommandWrapper()
        channel.configureBlocking(false)
        val key = channel.register(selector, SelectionKey.OP_READ)
        for (i in 1..3) {
            sendBuffer.position(0)
            channel.send(sendBuffer, address)
            try {
                selector.select(10000)
                if (key.isReadable) {
                    channel.receive(receiveBuffer)
                    result = decodeFromString<CommandWrapper>(bytes.decodeToString().replace("\u0000", ""))
                    break
                } else {
                    throw ConnectionException()
                }
            } catch (_: ConnectionException) {
                if (i == 3) {
                    throw ConnectionException()
                }
                io.write("Не удалось установить соединение с сервером. Попытка ${i+1} из 3.\n")
                continue
            }
        }
        return result
    }
}
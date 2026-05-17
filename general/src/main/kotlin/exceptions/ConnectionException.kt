package exceptions

class ConnectionException(): Exception() {
    override val message: String = "Не удалось установить соединение."
}
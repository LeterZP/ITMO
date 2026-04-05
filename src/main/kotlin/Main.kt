import core.CollectionManager
import core.InteractiveMode
import io.IOManager

fun main() {
    val io: IOManager = IOManager()
    val cm: CollectionManager = CollectionManager(io)
    val im: InteractiveMode = InteractiveMode(cm, io)
    im.start()
}
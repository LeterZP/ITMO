package exceptions

/**
 * Исключение, показывающее, что в коллекции нет элемента с таким [id][elements.City.id].
 *
 * Наследуется от класса [Exception].
 *
 * @param id [id] элемента типа [id][elements.City.id].
 *
 * @property message Сообщение об ошибке типа [String].
 *
 * @constructor Принимает все перечисленные выше параметры.
 *
 * @since 1.0
 */
class CollectionHasNoElementException(private val id: Long): Exception() {
    override val message: String = "У коллекции нет элемента с id $id"
}
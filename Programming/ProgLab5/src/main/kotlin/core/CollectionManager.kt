package core

import elements.City
import elements.Government
import exceptions.CollectionHasNoElementException
import io.IOManager
import java.io.IOException
import java.util.Stack
import java.time.LocalDate

/**
 * Класс для управления коллекцией, содержащей элементы типа [City].
 *
 * @param io [IOManager], с которым взаимодействует коллекция.
 *
 * @property initializationTime Время инициализации коллекции типа [LocalTime].
 *
 * @constructor Принимает все указанные выше параметры, создавая готовый к использованию объект
 *              и сразу загружая элементы из файла.
 *
 * @since 1.0
 */
class CollectionManager(private val io: IOManager) {
    private val save: String = System.getenv("SAVE_FILE") ?: "save.json"
    private var collection: Stack<City> = Stack<City>()
    val initializationTime: LocalDate = LocalDate.now()

    init {
        val previousSource = io.source
        io.source = save
        try {
            collection = io.readAsJsonFile()
            io.source = previousSource
        } catch (e: IOException) {
            io.source = previousSource
            io.write("Файл сохранения не найден.\n")
        }


    }

    /**
     * Считает количество элементов коллекции.
     *
     * @return Количество элементов коллекции типа [Int].
     *
     * @since 1.0
     */
    fun size(): Int {
        return collection.size
    }

    /**
     * Сохраняет хранящиеся в коллекции объекты в файл.
     *
     * @throws [java.io.IOException] В случае ошибки доступа к файлу.
     *
     * @since 1.0
     */
    fun saveToFile() {
        val previousSource: String? = io.source
        io.source = save
        io.writeToJsonFile(collection)
        io.source = previousSource
    }

    /**
     * Сортирует элементы коллекции.
     *
     * @since 1.0
     */
    fun sortElements() {
        collection.sort()
    }

    /**
     * Добавляет элемент в коллекцию.
     *
     * @param city Город типа [City], который нужно добавить в коллекцию.
     *
     * @since 1.0
     */
    fun addElement(city: City) {
        collection.push(city)
    }

    /**
     * Переворачивает коллекцию.
     *
     * @since 1.0
     */
    fun reorderElements() {
        val newCollection: Stack<City> = Stack<City>()
        while (!collection.isEmpty()) {
            newCollection.push(collection.pop())
        }
        collection = newCollection
    }

    /**
     * Считает количество элементов коллекции, высота над уровнем моря которого выше заданного.
     *
     * @param metersAboveSeaLevel Высота над уровнем моря типа [Long].
     *
     * @return Количество элементов коллекции, подходящих по условию, типа [Int].
     *
     * @since 1.0
     */
    fun countHigherThen(metersAboveSeaLevel: Long): Int {
        var count: Int = 0
        for (element in collection) {
            if (element.metersAboveSeaLevel > metersAboveSeaLevel) {
                count++
            }
        }
        return count
    }

    /**
     * Выдаёт элемент из коллекции по [id][City.id].
     *
     * @param id [id][City.id] города [City].
     *
     * @return Элемент коллекции типа [City].
     *
     * @throws CollectionHasNoElementException В случае, если в коллекции нет элемента с таким [id].
     *
     * @since 1.0
     */
    fun getElement(id: Long): City {
        if (collection.empty()) throw CollectionHasNoElementException(id)
        var element: City? = null
        for (e in collection) {
            if (e.id == id) element = e
        }
        return element?: throw CollectionHasNoElementException(id)
    }

    /**
     * Выдаёт все элементы коллекции в строковом представлении.
     *
     * @return Все элементы коллекции типа [String].
     *
     * @since 1.0
     */
    fun getAllElementsToString(): String {
        var result: String = ""
        for (element in collection) {
            if (result != "") result += "\n"
            result += element.toString()
        }
        return result
    }

    /**
     * Выдаёт все виды правительств элементов коллекции в сортированном виде.
     *
     * @return [ArrayList], содержащий все виды правительств типа [Government].
     *
     * @since 1.0
     */
    fun getSortedGovernments(): MutableList<Government?> {
        val governments: MutableList<Government?> = ArrayList()
        collection.sortWith(Comparator { city1, city2 -> compareValues(city1.government, city2.government) })
        for (element in collection) {
            governments.add(element.government)
        }
        sortElements()
        return governments
    }

    /**
     * Выдаёт сгруппированные имена всех городов и количество городов с одинаковым именем.
     *
     * @return [HashMap] с парами элементов имя типа [String] и количество типа [Int].
     *
     * @since 1.0
     */
    fun groupElements(): HashMap<String, Int> {
        val names: HashMap<String, Int> = HashMap()
        for (element in collection) {
            names[element.name] = names[element.name] ?: 0
            names[element.name] = names[element.name]!! + 1
        }
        return names
    }

    /**
     * Удаляет элемент из коллекции по [id][City.id].
     *
     * @param id [id][City.id] города [City].
     *
     * @throws CollectionHasNoElementException В случае, если в коллекции нет элемента с таким [id].
     *
     * @since 1.0
     */
    fun removeElement(id: Long) {
        if (!collection.remove(this.getElement(id))) throw CollectionHasNoElementException(id)
    }

    /**
     * Удаляет последний элемент коллекции.
     *
     * @throws CollectionHasNoElementException В случае, если коллекция пуста.
     *
     * @since 1.0
     */
    fun removeLast() {
        if (collection.empty()) throw CollectionHasNoElementException(-1)
        collection.remove(collection.last())
    }

    /**
     * Удаляет элементы, [id][City.id] которых больше заданного.
     *
     * @param id [id][City.id] города [City].
     *
     * @return Количество типа [Int] удалённых элементов.
     *
     * @since 1.0
     */
    fun removeGreater(id: Long): Int {
        var count: Int = 0
        sortElements()
        while (!collection.isEmpty()) {
            val element: City = collection.peek()
            if (element.id <= id) break
            collection.pop()
            count++
        }
        return count
    }

    /**
     * Удаляет все элементы коллекции.
     *
     * @since 1.0
     */
    fun clearCollection() {
        collection.clear()
    }
}
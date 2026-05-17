package elements

import exceptions.InvalidElementValueException
import java.time.LocalDate
import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual

/**
 * Город.
 *
 * Элемент коллекции.
 *
 * @param name Название города типа [String].
 * @param coordinates Координаты города типа [Coordinates].
 * @param area Площадь города типа положительный [Double].
 * @param population Население города типа положительный [Int].
 * @param metersAboveSeaLevel Высота над уровнем моря типа [Long].
 * @param populationDensity Плотность населения типа положительный [Float].
 * @param governon Губернатор города типа [Human].
 * @param climate Климат города типа [Climate], может быть null.
 * @param government Правительство типа [Government], может быть null.
 *
 * @property id Уникальный номер города типа положительный [Long].
 * @property creationDate Дата создания города типа [LocalDate].
 *
 * @constructor Принимает все параметры, описанные выше, создавая полноценный объект.
 *
 * @since 1.0
 */
@Serializable
class City(
    val name1: String,
    var coordinates: Coordinates,
    val area1: Double,
    val population1: Int,
    var metersAboveSeaLevel: Long,
    val populationDensity1: Float,
    var governon: Human,
    var climate: Climate? = null,
    var government: Government? = null
): Comparable<City> {

    var name: String = name1
        set(value) {
            if (value == "") throw InvalidElementValueException(value)
            field = value
        }
    var area: Double = area1
        set(value) {
            if (value <= 0) throw InvalidElementValueException(value)
            field = value
        }
    var population: Int = population1
        set(value) {
            if (value <= 0) throw InvalidElementValueException(value)
            field = value
        }
    var populationDensity: Float = populationDensity1
        set(value) {
            if (value <= 0) throw InvalidElementValueException(value)
            field = value
        }
    val id: Long = counter
    @Contextual
    val creationDate: LocalDate = LocalDate.now()

    companion object { private var counter: Long = 1 }

    init{
        if (id >= counter) {
            counter = id + 1
        }
    }

    override fun compareTo(other: City): Int {
        return this.id.compareTo(other.id)
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other is City) {
            if (this.id == other.id) return true
        }
        return false
    }

    override fun toString(): String {
        var output: String = """
           --$id: Город $name 
                был создан $creationDate
                расположен по координатам $coordinates 
                $metersAboveSeaLevel метров над уровнем моря
                занимает площадь $area
                с населением $population и его плотностью $populationDensity
                управляет им $governon
        """.trimIndent()
        if (government != null) {
            output += "\n     с правительством $government"
        }
        if (climate != null) {
            output += "\n     да и погода там $climate"
        }
        return output
    }
}
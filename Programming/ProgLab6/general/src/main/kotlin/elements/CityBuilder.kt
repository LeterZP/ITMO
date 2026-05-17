package elements

import exceptions.InvalidElementValueException

/**
 * Builder для класса [City].
 *
 * Может поэтапно создавать и изменять объекты класса [City].
 *
 * @property size Количество свойств типа [Int], необходимых для создания [City].
 *
 * @since 1.0
 */
class CityBuilder {
    private var name: String? = null
        set(value) {
            if (value == null) throw InvalidElementValueException("")
            field = value
        }
    private var coordinateX: Int? = null
        set(value) {
            if (value is Int && value > -827) field = value
            else throw InvalidElementValueException(value?: "")
        }
    private var coordinateY: Double? = null
        set(value) {
            if (value == null) throw InvalidElementValueException("")
            field = value
        }
    private var area: Double? = null
        set(value) {
            if (value is Double && value > 0) field = value
            else throw InvalidElementValueException(value?: "")
        }
    private var population: Int? = null
        set(value) {
            if (value is Int && value > 0) field = value
            else throw InvalidElementValueException(value?: "")
        }
    private var metersAboveSeaLevel: Long? = null
        set(value) {
            if (value == null) throw InvalidElementValueException("")
            field = value
        }
    private var populationDensity: Float? = null
        set(value) {
            if (value is Float && value > 0) field = value
            else throw InvalidElementValueException(value?: "")
        }
    private var govName: String? = null
        set(value) {
            if (value == null) throw InvalidElementValueException("")
            field = value
        }
    private var govAge: Long? = null
        set(value) {
            if (value is Long && value > 0) field = value
            else throw InvalidElementValueException(value?: "")
        }
    private var govHeight: Float? = null
        set(value) {
            if (value is Float && value > 0) field = value
            else throw InvalidElementValueException(value?: "")
        }
    private var climate: Climate? = null
    private var government: Government? = null
    private val fields: Array<String> = arrayOf(
        "название города (String)",
        "координата X (Int)",
        "координата Y (Double)",
        "площадь (Double)",
        "население (Int)",
        "высоту над уровнем моря (Long)",
        "плотность населения (Float)",
        "имя губернатора (String)",
        "возраст губернатора (Long)",
        "рост губернатора (Float)",
        "климат (RAIN_FOREST | MONSOON | HUMIDCONTINENTAL | SUBARCTIC | TUNDRA)",
        "правительство (ARISTOCRACY | ANARCHY | KLEPTOCRACY | CORPORATOCRACY | JUNTA)"
    )
    val size: Int = 12

    /**
     * Используется для получения названия и типа свойства класса.
     *
     * @param count Номер свойства типа [Int].
     *
     * @return Имя и тип свойства типа [String].
     *
     * @since 1.0
     */
    fun getField(count: Int): String {
        return fields[count]
    }

    /**
     * Используется для установки значения свойства.
     *
     * @param input Новое значение свойства типа [String].
     * @param count Номер свойства типа [Int].
     *
     * @throws InvalidElementValueException В случае, если значение не может быть установлено.
     *
     * @since 1.0
     */
    fun setField(input: String, count: Int) {
        val value: String? = if (input == "") null else input
        try {
            when (count) {
                0 -> name = value
                1 -> coordinateX = value?.toInt()
                2 -> coordinateY = value?.toDouble()
                3 -> area = value?.toDouble()
                4 -> population = value?.toInt()
                5 -> metersAboveSeaLevel = value?.toLong()
                6 -> populationDensity = value?.toFloat()
                7 -> govName = value
                8 -> govAge = value?.toLong()
                9 -> govHeight = value?.toFloat()
                10 -> climate = run {
                    val result: Climate? = if (value != null) Climate.valueOf(value.uppercase()) else null
                    result
                }

                11 -> government = run {
                    val result: Government? = if (value != null) Government.valueOf(value.uppercase()) else null
                    result
                }
            }
        } catch (_: NumberFormatException) {
            throw InvalidElementValueException(value?:"")
        } catch (_: IllegalArgumentException) {
            throw InvalidElementValueException(value?:"")
        }
    }

    /**
     * Создаёт объект типа [City] на основе загруженных полей.
     *
     * @return Город типа [City].
     *
     * @throws InvalidElementValueException В случае, если какое-либо из установленных значений не подходит под
     *                                      требования класса [City].
     *
     * @since 1.0
     */
    fun create(): City {
        if (name != null
            && coordinateX != null
            && coordinateY != null
            && area != null
            && population != null
            && metersAboveSeaLevel != null
            && populationDensity != null
            && govName != null
            && govAge != null
            && govHeight != null
        ) {
            val cords = Coordinates(coordinateX!!, coordinateY!!)
            val governon = Human(govName!!, govAge!!, govHeight!!)
            return City(name!!, cords, area!!,
                population!!, metersAboveSeaLevel!!, populationDensity!!,
                governon, climate, government)
        } else throw InvalidElementValueException("City")
    }

    /**
     * Изменяет свойства уже имеющегося объекта типа [City].
     *
     * @param city Город для изменения типа [City].
     *
     * @since 1.0
     */
    fun update(city: City) {
        if (name != null) city.name = name!!
        if (coordinateX != null && coordinateY != null)
            city.coordinates = Coordinates(coordinateX!!, coordinateY!!)
        if (area != null) city.area = area!!
        if (population != null) city.population = population!!
        if (metersAboveSeaLevel != null) city.metersAboveSeaLevel = metersAboveSeaLevel!!
        if (populationDensity != null) city.populationDensity = populationDensity!!
        if (govName != null && govAge != null && govHeight != null)
            city.governon = Human(govName!!, govAge!!, govHeight!!)
        if (climate != null) city.climate = climate
        if (government != null) city.government = government
    }
}
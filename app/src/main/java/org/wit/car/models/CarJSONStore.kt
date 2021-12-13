package org.wit.car.models


import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.car.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "cars.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<CarModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class CarJSONStore(private val context: Context) : CarStore {

    var cars = mutableListOf<CarModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CarModel> {
        logAll()
        return cars
    }

    override fun create(car: CarModel) {
        car.id = generateRandomId()
        cars.add(car)
        serialize()
    }


    override fun update(car: CarModel) {
        var foundCar: CarModel? = cars.find { c -> c.id == car.id }
        if (foundCar != null) {
            foundCar.model = car.model
            foundCar.brand = car.brand
            foundCar.year = car.year
            foundCar.plateNumber = car.plateNumber
            foundCar.image = car.image
            foundCar.lat = car.lat
            foundCar.lng = car.lng
            foundCar.zoom = car.zoom
            logAll()
            serialize()
        }

    }

    override fun delete(car: CarModel) {
        cars.remove(car)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(cars, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        cars = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        cars.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}
package org.wit.car.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class CarMemStore : CarStore {

    val cars = ArrayList<CarModel>()

    override fun findAll(): List<CarModel> {
        return cars
    }

    override fun create(car: CarModel) {
        car.id = getId()
        cars.add(car)
        logAll()
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
        }
    }

    override fun delete(car: CarModel) {
        cars.remove(car)
    }

    fun logAll() {
        cars.forEach{ i("${it}") }
    }
}
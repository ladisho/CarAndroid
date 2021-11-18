package org.wit.car.models

import timber.log.Timber.i

class CarMemStore : CarStore {

    val cars = ArrayList<CarModel>()

    override fun findAll(): List<CarModel> {
        return cars
    }

    override fun create(car: CarModel) {
        cars.add(car)
        logAll()
    }

    fun logAll() {
        cars.forEach{ i("${it}") }
    }
}
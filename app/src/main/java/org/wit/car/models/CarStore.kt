package org.wit.car.models

interface CarStore {
    fun findAll(): List<CarModel>
    fun create(car: CarModel)
}
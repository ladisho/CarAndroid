package org.wit.car.models

interface CarStore {
    fun findAll(): List<CarModel>
    fun create(car: CarModel)
    fun update(car: CarModel)
    fun delete(car: CarModel)
}
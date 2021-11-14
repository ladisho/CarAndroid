package org.wit.car.main

import android.app.Application
import org.wit.car.models.CarModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val cars = ArrayList<CarModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Car started")
        cars.add(CarModel("One", "Audi"))
        cars.add(CarModel("Two", "Toyota"))
        cars.add(CarModel("Three", "Mazda"))
    }
}
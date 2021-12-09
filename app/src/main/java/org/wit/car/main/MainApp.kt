package org.wit.car.main

import android.app.Application
import org.wit.car.models.CarMemStore
import org.wit.car.models.CarStore
//import org.wit.car.models.CarModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

//    val cars = ArrayList<CarModel>()
    lateinit var cars: CarStore


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        cars = CarMemStore()
        i("Car started")

    }
}
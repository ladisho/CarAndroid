package org.wit.car.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.wit.car.R
import org.wit.car.main.MainApp

class CarListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_list)
        app = application as MainApp
    }
}
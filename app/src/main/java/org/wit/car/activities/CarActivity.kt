package org.wit.car.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.car.R
import org.wit.car.databinding.ActivityCarBinding
import org.wit.car.models.CarModel
import timber.log.Timber
import timber.log.Timber.i



class CarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarBinding
    var car = CarModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)

        binding = ActivityCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Car Activity started...")

        binding.btnAdd.setOnClickListener() {
            car.model = binding.carModel.text.toString()
            if (car.model.isNotEmpty()) {
                i("add Button Pressed: ${car.model}")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a model", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }
}
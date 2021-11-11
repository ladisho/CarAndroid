package org.wit.car

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.wit.car.databinding.ActivityCarBinding
import timber.log.Timber
import timber.log.Timber.i



class CarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)

        binding = ActivityCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Car Activity started...")

        binding.btnAdd.setOnClickListener() {
            val carModel = binding.carModel.text.toString()
            if (carModel.isNotEmpty()) {
                i("add Button Pressed: $carModel")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a model", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }
}
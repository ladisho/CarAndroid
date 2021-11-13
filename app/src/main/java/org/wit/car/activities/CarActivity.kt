package org.wit.car.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.wit.car.R
import org.wit.car.databinding.ActivityCarBinding
import org.wit.car.main.MainApp
import org.wit.car.models.CarModel
import timber.log.Timber
import timber.log.Timber.i


class CarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarBinding
    var car = CarModel()
//    val cars = ArrayList<CarModel>()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_car)




//        Timber.plant(Timber.DebugTree())
//
//        i("Car Activity started...")
        app = application as MainApp
        binding.btnAdd.setOnClickListener() {
            car.model = binding.carModel.text.toString()
            car.brand = binding.carBrand.text.toString()
            if (binding.carYear.text.isNotEmpty() && binding.carYear.text.length == 4){
                car.year = Integer.parseInt(binding.carYear.text.toString())
            }else{
                car.year = 9999
            }

            if(binding.carPlateNumber.text.isNotEmpty() && binding.carPlateNumber.text.length == 11){
                car.plateNumber = binding.carPlateNumber.text.toString()
            }else{
                car.plateNumber = "999-W-99999"
            }

            var msg:String =""
            if (car.model.isNotEmpty()) {
                i("add Button Pressed: ${car.model}")


            }
            else {
                msg += "Please enter a model\n"
//                Snackbar
//                    .make(it,"Please Enter a model", Snackbar.LENGTH_LONG)
//                    .show()
            }
            if (car.brand.isNotEmpty()) {
                i("add Button Pressed: ${car.brand}")

            }
            else {
                msg += "Please enter a brand\n"
//                Snackbar
//                    .make(it,"Please Enter a brand", Snackbar.LENGTH_LONG)
//                    .show()
            }
            if (car.year > 0) {
                i("add Button Pressed: ${car.year}")

            }
            else {
                msg += "Please enter a year\n"
//                Snackbar
//                    .make(it,"Please Enter a brand", Snackbar.LENGTH_LONG)
//                    .show()
            }
            if (car.plateNumber.isNotEmpty()) {
                i("add Button Pressed: ${car.plateNumber}")
            }
            else {
                msg += "Please enter a plateNumber"
//                Snackbar
//                    .make(it,"Please Enter a brand", Snackbar.LENGTH_LONG)
//                    .show()
            }
            if (car.model.isNotEmpty() && car.brand.isNotEmpty() && car.year > 0 && car.plateNumber.isNotEmpty()){
                i("add Button Pressed: ${car.model} ${car.brand} ${car.year} ${car.plateNumber}")
                app.cars.add(car.copy())
                for (i in app.cars.indices){
                    i("Car[$i]: ${this.app.cars[i]}")
                }


            }

            if(msg.isNotEmpty()){
                Snackbar
                    .make(it,msg, Snackbar.LENGTH_LONG)
                    .show()

            }
        }

    }
}
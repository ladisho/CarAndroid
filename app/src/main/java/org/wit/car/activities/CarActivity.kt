package org.wit.car.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
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

            }
            if (car.brand.isNotEmpty()) {
                i("add Button Pressed: ${car.brand}")

            }
            else {
                msg += "Please enter a brand\n"

            }
            if (car.year > 0) {
                i("add Button Pressed: ${car.year}")

            }
            else {
                msg += "Please enter a year\n"

            }
            if (car.plateNumber.isNotEmpty()) {
                i("add Button Pressed: ${car.plateNumber}")
            }
            else {
                msg += "Please enter a plateNumber"

            }
            if (car.model.isNotEmpty() && car.brand.isNotEmpty() && car.year > 0 && car.plateNumber.isNotEmpty()){
                i("add Button Pressed: ${car.model} ${car.brand} ${car.year} ${car.plateNumber}")
//                app.cars.add(car.copy())
                app.cars.create(car.copy())
                setResult(RESULT_OK)
                finish()


            }

            if(msg.isNotEmpty()){
                Snackbar
                    .make(it,msg, Snackbar.LENGTH_LONG)
                    .show()

            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activities, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
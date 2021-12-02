package org.wit.car.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.car.R
import org.wit.car.databinding.ActivityCarBinding
import org.wit.car.helpers.showImagePicker
import org.wit.car.main.MainApp
import org.wit.car.models.CarModel
import timber.log.Timber
import timber.log.Timber.i


class CarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarBinding
    var car = CarModel()
//    val cars = ArrayList<CarModel>()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        var carModel: String = getString(R.string.str_enterCarModel)
        var carBrand: String = getString(R.string.str_enterCarBrand)
        var carYear: String = getString(R.string.str_enterCarYear)
        var carPlateNumber: String = getString(R.string.str_enterCarPlateNumber)
        var edit = false

        super.onCreate(savedInstanceState)
        binding = ActivityCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp

        if (intent.hasExtra("car_edit")) {
            edit = true
            car = intent.extras?.getParcelable("car_edit")!!
            binding.carModel.setText(car.model)
            binding.carBrand.setText(car.brand)
            binding.carYear.setText(car.year.toString())
            binding.carPlateNumber.setText(car.plateNumber)
            binding.btnAdd.setText(R.string.txt_saveCar)
            Picasso.get()
                .load(car.image)
                .into(binding.carImage)
            binding.chooseImage.setText(R.string.button_changeImage)
        }

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
                msg += "$carModel \n"

            }
            if (car.brand.isNotEmpty()) {
                i("add Button Pressed: ${car.brand}")

            }
            else {
                msg += "$carBrand \n"

            }
            if (car.year > 0) {
                i("add Button Pressed: ${car.year}")

            }
            else {
                msg += "$carYear \n"

            }
            if (car.plateNumber.isNotEmpty()) {
                i("add Button Pressed: ${car.plateNumber}")
            }
            else {
                msg += "$carPlateNumber"

            }
            if (car.model.isNotEmpty() && car.brand.isNotEmpty() && car.year > 0 && car.plateNumber.isNotEmpty()){
                i("add Button Pressed: ${car.model} ${car.brand} ${car.year} ${car.plateNumber}")
//                app.cars.add(car.copy())
                if (edit){
                    app.cars.update(car.copy())
                }else{
                    app.cars.create(car.copy())
                }
                setResult(RESULT_OK)
                finish()


            }

            if(msg.isNotEmpty()){
                Snackbar
                    .make(it,msg, Snackbar.LENGTH_LONG)
                    .show()

            }
        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }

        registerImagePickerCallback()

        binding.carLocation.setOnClickListener {
            i ("Set Location Pressed")
        }

        registerMapCallback()

        binding.carLocation.setOnClickListener {
            val launcherIntent = Intent(this, MapActivity::class.java)
            mapIntentLauncher.launch(launcherIntent)
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

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            car.image = result.data!!.data!!
                            Picasso.get()
                                .load(car.image)
                                .into(binding.carImage)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { i("Map Loaded") }
    }
}
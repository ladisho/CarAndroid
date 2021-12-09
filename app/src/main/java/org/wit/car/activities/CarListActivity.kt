package org.wit.car.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.car.R
import org.wit.car.adapters.CarAdapter
import org.wit.car.adapters.CarListener
import org.wit.car.databinding.ActivityCarListBinding
import org.wit.car.main.MainApp
import org.wit.car.models.CarModel


class CarListActivity : AppCompatActivity(), CarListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityCarListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadCars()

        registerRefreshCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, CarActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCarClick(car: CarModel) {
        val launcherIntent = Intent(this, CarActivity::class.java)
        launcherIntent.putExtra("car_edit", car)
        refreshIntentLauncher.launch(launcherIntent)
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadCars() }
    }

    private fun loadCars() {
        showCars(app.cars.findAll())
    }

    fun showCars (cars: List<CarModel>) {
        binding.recyclerView.adapter = CarAdapter(cars, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}


package org.wit.car.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.car.R
import org.wit.car.databinding.ActivityCarListBinding
import org.wit.car.databinding.CardCarBinding
import org.wit.car.main.MainApp
import org.wit.car.models.CarModel

class CarListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityCarListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)


        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = CarAdapter(app.cars)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

class CarAdapter constructor(private var cars: List<CarModel>) :
    RecyclerView.Adapter<CarAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardCarBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val car = cars[holder.adapterPosition]
        holder.bind(car)
    }

    override fun getItemCount(): Int = cars.size

    class MainHolder(private val binding : CardCarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: CarModel) {
            binding.carModel.text = car.model
            binding.carBrand.text = car.brand
        }
    }
}
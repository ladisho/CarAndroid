package org.wit.car.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.wit.car.databinding.CardCarBinding
import org.wit.car.models.CarModel

interface CarListener {
    fun onCarClick(car: CarModel)
}

class CarAdapter constructor(private var cars: List<CarModel>,
                            private val listener: CarListener) :
    RecyclerView.Adapter<CarAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardCarBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val car = cars[holder.adapterPosition]
        holder.bind(car, listener)
    }

    override fun getItemCount(): Int = cars.size

    class MainHolder(private val binding : CardCarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: CarModel, listener: CarListener) {
            binding.carModel.text = car.model
            binding.carBrand.text = car.brand
            binding.carYear.text = car.year.toString()
            binding.carPlateNumber.text = car.plateNumber
            Picasso.get()
                .load(car.image)
                .into(binding.imageView)
            binding.root.setOnClickListener { listener.onCarClick(car) }
        }
    }
}
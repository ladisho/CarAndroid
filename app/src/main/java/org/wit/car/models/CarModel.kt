package org.wit.car.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarModel(var id: Long = 0,
                    var model: String = "",
                    var brand: String = "",
                    var year: Int = 0,
                    var plateNumber: String = "",
                    var image: Uri = Uri.EMPTY
) : Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable

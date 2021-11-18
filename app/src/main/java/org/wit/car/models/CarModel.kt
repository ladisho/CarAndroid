package org.wit.car.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarModel(var id: Long = 0,
                    var model: String = "",
                    var brand: String = "",
                    var year: Int = 0,
                    var plateNumber: String = ""
) : Parcelable

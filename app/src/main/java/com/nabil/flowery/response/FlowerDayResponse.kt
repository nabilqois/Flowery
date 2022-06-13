package com.nabil.flowery.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlowerDayResponse(
    var error: String? = null,
    var message: String? = null,
    var result: List<FlowerDay>
): Parcelable
@Parcelize
data class FlowerDay(
    var _id: String? = null,
    var global_name: String? = null,
    var scientific_name: String? = null,
    var images:String? = null
): Parcelable
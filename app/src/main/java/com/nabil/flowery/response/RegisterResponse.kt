package com.nabil.flowery.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class RegisterResponse(
    val error:String,
    val message:String
)
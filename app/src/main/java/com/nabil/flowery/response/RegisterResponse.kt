package com.nabil.flowery.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class RegisterResponse(
    val name:String,
    val email:String
)
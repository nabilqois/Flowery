package com.nabil.flowery.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    var error: String? = null,
    var message: String? = null,
    var result: LoginResult
): Parcelable

@Parcelize
data class LoginResult(
    var user_id: String? = null,
    var user_name: String? = null,
    var token: String? = null
): Parcelable
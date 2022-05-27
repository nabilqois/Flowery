package com.nabil.flowery.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    var error: String? = null,
    var message: String? = null,
    var loginResult: LoginResult
): Parcelable

@Parcelize
data class LoginResult(
    var userId: String? = null,
    var name: String? = null,
    var token: String? = null
): Parcelable
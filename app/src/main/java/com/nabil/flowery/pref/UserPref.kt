package com.nabil.flowery.pref

import android.content.Context
import com.nabil.flowery.response.LoginResult

class UserPref(context: Context) {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    fun setResponseLogin(value: LoginResult) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(TOKEN, value.token)
        editor.apply()
    }

    fun getResponseLogin(): String {
        val data: String? = preferences.getString(TOKEN, "")
        return data.toString()
    }

    fun isLoggedIn(): Boolean {
        return preferences.getBoolean(IS_LOGIN, false)
    }

    fun logout() {
        editor.clear()
        editor.apply()

    }

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val NAME = "name"
        private const val TOKEN = "token"
        private const val IS_LOGIN = "isLoggedIn"
    }
}
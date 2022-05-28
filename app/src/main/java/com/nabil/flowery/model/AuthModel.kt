package com.nabil.flowery.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nabil.flowery.response.LoginResponse
import com.nabil.flowery.response.LoginResult
import com.nabil.flowery.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthModel : ViewModel(){
    private val _loginData = MutableLiveData<LoginResult>()
    val loginData: LiveData<LoginResult> = _loginData

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    fun getLoginData(email: String, password: String) {
        ApiConfig.getApiService()
            .login(email, password)
            .enqueue(object : Callback<LoginResult>{
                override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                    if (response.isSuccessful) {
                        _loginData.value = response.body()
                        _isError.value = false
                    }
                }

                override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                    Log.d("LoginModel", "onFailure: Terjadi kesalahan")
                    _isError.value = true
                }

            })
    }
}
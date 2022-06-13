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
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        _loginData.value = response.body()?.result
                        _isError.value = false
                    } else {
                        //TODO
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("LoginModel", "onFailure: ${t.message}")
                    _isError.value = true
                }

            })

    }
}
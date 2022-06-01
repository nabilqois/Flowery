package com.nabil.flowery.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.nabil.flowery.response.RegisterResponse
import com.nabil.flowery.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthModelRegis: ViewModel() {

    fun getRegisterData(name:String,email:String,password:String){
        ApiConfig.getApiService()
            .register(name, email, password)
            .enqueue(object: Callback<RegisterResponse>
            {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    val user = response.body()
                    if(user != null){
                        TODO()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.d("Register Failure", t.message.toString())
                }

            })
    }
}
package com.nabil.flowery.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nabil.flowery.response.ListTrivia
import com.nabil.flowery.response.TriviaResponse
import com.nabil.flowery.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TriviaModel:ViewModel() {

    private val _listTrivia = MutableLiveData<List<ListTrivia>>()
    val listTrivia: LiveData<List<ListTrivia>> = _listTrivia


    fun getFlowerTrivia(token: String) {
        ApiConfig.getApiService()
            .getTrivia(token)
            .enqueue(object : Callback<TriviaResponse> {
                override fun onResponse(
                    call: Call<TriviaResponse>,
                    response: Response<TriviaResponse>
                ) {
                    if (response.isSuccessful) {
                        _listTrivia.value = response.body()?.result
                        Log.e("S", _listTrivia.value.toString())
                    }
                }

                override fun onFailure(call: Call<TriviaResponse>, t: Throwable) {
                    Log.d("Trivia Failure", t.message.toString())
                }

            })
    }



}
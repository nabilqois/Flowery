package com.nabil.flowery.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nabil.flowery.response.FlowerDay
import com.nabil.flowery.response.FlowerDayResponse
import com.nabil.flowery.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FlowerModel: ViewModel() {

    private val _listFlower = MutableLiveData<List<FlowerDay>>()
    val listFlower: LiveData<List<FlowerDay>> = _listFlower


    fun getFlower(token: String) {
        ApiConfig.getApiService()
            .getFlowerOfTheDay(token)
            .enqueue(object : Callback<FlowerDayResponse> {
                override fun onResponse(
                    call: Call<FlowerDayResponse>,
                    response: Response<FlowerDayResponse>
                ) {
                    if (response.isSuccessful) {
                        _listFlower.value = response.body()?.result
                        Log.e("S", _listFlower.value.toString())
                    }
                }

                override fun onFailure(call: Call<FlowerDayResponse>, t: Throwable) {
                    Log.d("Trivia Failure", t.message.toString())
                }

            })
    }
}
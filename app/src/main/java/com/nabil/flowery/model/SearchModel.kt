package com.nabil.flowery.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nabil.flowery.response.ListFlower
import com.nabil.flowery.response.SearchResponse
import com.nabil.flowery.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchModel: ViewModel() {
    private val _listFlower = MutableLiveData<List<ListFlower>>()
    val listFlower: LiveData<List<ListFlower>> = _listFlower

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun getListFlower(token: String, kueri: String) {
        ApiConfig.getApiService()
            .getListFlower(token, kueri)
            .enqueue(object : Callback<SearchResponse>{
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful) {

                        _isError.value = false

                        _message.value = response.body()?.message!!.toString()
                        Log.d("SearchModel", "message: ${response.body()?.message!!.toString()}")

                        Log.d("SearchModel", "getListFLower ${response.body()}")
                        Log.d("SearchModel", "successful :  $token")

                        _listFlower.value = response.body()?.result
                        Log.d("SearchModel", "result :  ${response.body()?.result}")

                    } else {
                        _isError.value = true
                        Log.d("SearchModel", "not success :  $token")
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    _isError.value = true
                    Log.d("SearchModel", "onFailure: ${t.message.toString()}")
                }

            })
    }
}
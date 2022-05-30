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

    fun getListFlower(token: String, kueri: String) {
        ApiConfig.getApiService()
            .getListFlower(token, kueri)
            .enqueue(object : Callback<SearchResponse>{
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("SearchModel", "getListFLower ${response.body()}")
                        Log.d("SearchModel", "token :  $token")
                        _listFlower.value = response.body()?.result
                        _isError.value = false
                    } else {
                        _isError.value = true
                        Log.d("SearchModel", "token :  $token")
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Log.d("Search Failure", t.message.toString())
                }

            })
    }
}
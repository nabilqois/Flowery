package com.nabil.flowery.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nabil.flowery.response.TutorialResponse
import com.nabil.flowery.response.TutorialResult
import com.nabil.flowery.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TutorialModel : ViewModel() {
    private val _listTutorial = MutableLiveData<TutorialResult>()
    val listTutorial: LiveData<TutorialResult> = _listTutorial

    fun getTutorialFlower(token : String) {
        ApiConfig.getApiService().getTutorial(token)
            .enqueue(object : Callback<TutorialResponse> {
                override fun onResponse(
                    call: Call<TutorialResponse>,
                    response: Response<TutorialResponse>
                ) {
                    if (response.isSuccessful) {
                        _listTutorial.value = response.body()?.result
                        Log.d("TutorialModel berhasil", _listTutorial.toString())
                    } else {
                        Log.d("TutorialModel", "Terjadi kesalahan ${response.body()?.message}")
                    }
                }

                override fun onFailure(call: Call<TutorialResponse>, t: Throwable) {
                    Log.d("TutorialModel", "onFailure: ${t.message.toString()}")
                }

            })
    }
}
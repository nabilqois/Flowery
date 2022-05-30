package com.nabil.flowery.ui.camera

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nabil.flowery.adapter.FlowerAdapter
import com.nabil.flowery.databinding.ActivityResultBinding
import com.nabil.flowery.model.SearchModel
import com.nabil.flowery.pref.UserPref
import com.nabil.flowery.response.ListFlower
import com.nabil.flowery.util.rotateBitmap
import java.io.File

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private val searchModel: SearchModel by viewModels()

    private lateinit var flowerAdapter: FlowerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvFlowerList.setHasFixedSize(true)

        val activity = intent.extras?.getString(EXTRA_ACTIVITY)
        if (activity != null) {
            Log.d("intentResult", activity)
        }
        if (activity.equals("MainActivity")) {
            val kueri = intent.getStringExtra("kueri").toString()
            Log.d("ResultActivity", kueri)
            Log.d("testDataIntent", kueri)
            Toast.makeText(this, kueri, Toast.LENGTH_SHORT).show()

            setRecycleView()
            getListFlower(kueri)

            searchModel.listFlower.observe(this) { listFlower ->
                setListFlower(listFlower)
                if (flowerAdapter.itemCount == 0) {
                    Toast.makeText(this, "Hasil Tidak Ditemukan", Toast.LENGTH_LONG).show()
                }
            }

        } else {

            Log.d("testCamera", "Berhasil")

            val image = intent.extras?.get(EXTRA_IMAGE) as File
            val isBackCamera = intent.getBooleanExtra("isBackCamera", true)

            val resultImage = rotateBitmap(BitmapFactory.decodeFile(image.path), isBackCamera)

            binding.resultImage.setImageBitmap(resultImage)
        }
    }

    fun getListFlower(query: String) {
        val token = UserPref(this).getResponseLogin()
        Log.d("getToken", token)
        searchModel.getListFlower(token, query)
    }

    private fun setRecycleView() {
        flowerAdapter = FlowerAdapter(arrayListOf())
        binding.rvFlowerList.apply {
            layoutManager =
                LinearLayoutManager(applicationContext)
            adapter = flowerAdapter

        }
    }

    private fun setListFlower(flowers: List<ListFlower>) {
        flowerAdapter.setListFlower(flowers)
    }

    companion object {
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_ACTIVITY = "extra_activity"
    }
}
package com.nabil.flowery.ui.camera

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nabil.flowery.R
import com.nabil.flowery.adapter.FlowerAdapter
import com.nabil.flowery.databinding.ActivityResultBinding
import com.nabil.flowery.ml.Model
import com.nabil.flowery.model.SearchModel
import com.nabil.flowery.pref.UserPref
import com.nabil.flowery.response.ListFlower
import com.nabil.flowery.ui.DetailFlowerActivity
import com.nabil.flowery.util.rotateBitmap
import org.tensorflow.lite.support.image.TensorImage
import java.io.File

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private val searchModel: SearchModel by viewModels()

    private lateinit var flowerAdapter: FlowerAdapter
    private lateinit var result: String

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

            binding.resultImage.visibility = View.GONE
            binding.tvOutput.visibility = View.GONE

//            setRecycleView()
            getListFlower(kueri)

            searchModel.message.observe(this) {
                Log.d("ResultActivity", it)
                if (it.equals("success")) {
                    setRecycleView()
                    searchModel.listFlower.observe(this) { listFlower ->
                        setListFlower(listFlower)
                        Log.d("ResultActivity", "ListFlower: ${listFlower[0]}")
                        if (flowerAdapter.itemCount == 0) {
                            Toast.makeText(this, getString(R.string.not_found), Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    binding.tvOutput.text = getString(R.string.not_found)
                    Toast.makeText(this, getString(R.string.not_found), Toast.LENGTH_LONG).show()
                }
            }

            searchModel.isError.observe(this) {isError ->
                if (isError) {
                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }


        } else {

            Log.d("testCamera", "Berhasil")

            val image = intent.extras?.get(EXTRA_IMAGE) as File
            val isBackCamera = intent.getBooleanExtra("isBackCamera", true)

            val resultImage = rotateBitmap(BitmapFactory.decodeFile(image.path), isBackCamera)

            binding.resultImage.setImageBitmap(resultImage)

            outputGenerator(resultImage)

            Log.d("Result", "Result: $result")
            getListFlower(result)

            searchModel.message.observe(this) { message ->
                Log.d("ResultActivity", message)

                if (message.equals("success")) {
                    setRecycleView()
                    searchModel.listFlower.observe(this) { listFlower ->
                        setListFlower(listFlower)
                        if (flowerAdapter.itemCount == 0) {
                            Toast.makeText(this, getString(R.string.not_found), Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(this, getString(R.string.not_found), Toast.LENGTH_LONG).show()
                }
            }

            searchModel.isError.observe(this) {isError ->
                if (isError) {
                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }

    private fun getListFlower(query: String) {
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

            flowerAdapter.setOnItemClickCallback(object : FlowerAdapter.OnItemClickCallBack {
                override fun onItemClicked(data: ListFlower) {
                    Log.d("onItemClicked", "its clicked")
                    showSelectedFlower(data)
                }
            })
        }
    }

    private fun setListFlower(flowers: List<ListFlower>) {
        flowerAdapter.setListFlower(flowers)
    }

    private fun showSelectedFlower(data: ListFlower) {
        val toDetailFlower = Intent(this, DetailFlowerActivity::class.java)
        toDetailFlower.putExtra(DetailFlowerActivity.EXTRA_DETAIL, data)
        startActivity(toDetailFlower)
    }

    //Classifying Image with ML
    private fun outputGenerator(bitmap: Bitmap) {
        //Declaring TFLite model variable
        val model = Model.newInstance(this)

        // Converting Bitmap to tensorflow image
        val newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val image = TensorImage.fromBitmap(bitmap)

        // Runs model inference and gets result.
        val outputs = model.process(image).probabilityAsCategoryList.apply {
            sortByDescending { it.score }
        }

        //getting result from the one that have high probability
        val probability = outputs[0]
        result = probability.label


        //Setting output text
        binding.tvOutput.text = probability.label
        Log.i("TAG", "outputGenerator: $probability")

        // Releases model resources if no longer used.
        model.close()
    }

    companion object {
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_ACTIVITY = "extra_activity"
    }
}
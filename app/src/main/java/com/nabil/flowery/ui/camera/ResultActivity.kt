package com.nabil.flowery.ui.camera

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.nabil.flowery.databinding.ActivityResultBinding
import com.nabil.flowery.util.rotateBitmap
import java.io.File

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activity = intent.extras?.getString(EXTRA_ACTIVITY)
        if (activity != null) {
            Log.d("intentResult", activity)
        }
        if (activity.equals("MainActivity")) {
            val kueri = intent.getStringExtra("kueri").toString()
            Log.d("ResultActivity", kueri)
            Log.d("testDataIntent", kueri)
            Toast.makeText(this, kueri, Toast.LENGTH_SHORT).show()
        } else {

            Log.d("testCamera", "Berhasil")

            val image = intent.extras?.get(EXTRA_IMAGE) as File
            val isBackCamera = intent.getBooleanExtra("isBackCamera", true)

            val resultImage = rotateBitmap(BitmapFactory.decodeFile(image.path), isBackCamera)

            binding.resultImage.setImageBitmap(resultImage)
        }
    }

    companion object {
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_ACTIVITY = "extra_activity"
    }
}
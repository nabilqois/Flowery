package com.nabil.flowery.ui.camera

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nabil.flowery.databinding.ActivityResultBinding
import com.nabil.flowery.util.rotateBitmap
import java.io.File

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val image = intent.extras?.get(EXTRA_IMAGE) as File
        val isBackCamera = intent.getBooleanExtra("isBackCamera", true)

        val resultImage = rotateBitmap(BitmapFactory.decodeFile(image.path), isBackCamera)

        binding.resultImage.setImageBitmap(resultImage)
    }

    companion object {
        const val EXTRA_IMAGE = "extra_image"
    }
}
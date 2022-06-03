package com.nabil.flowery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.nabil.flowery.R
import com.nabil.flowery.databinding.ActivityDetailFlowerBinding
import com.nabil.flowery.response.ListFlower

class DetailFlowerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFlowerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFlowerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detail = intent.getParcelableExtra<ListFlower>(EXTRA_DETAIL) as ListFlower
        Toast.makeText(this, detail.toString(), Toast.LENGTH_SHORT).show()

        binding.apply {
            Glide.with(binding.root)
                .load(detail.images)
//                .circleCrop()
                .into(imgFlower)

            tvFlowerName.text = detail.local_name
            tvFlowerName2.text = detail.global_name
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}
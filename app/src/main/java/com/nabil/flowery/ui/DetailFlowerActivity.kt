package com.nabil.flowery.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.nabil.flowery.R
import com.nabil.flowery.databinding.ActivityDetailFlowerBinding
import com.nabil.flowery.model.DetailFlowerModel
import com.nabil.flowery.response.ListFlower
import com.nabil.flowery.util.getCurrentDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFlowerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFlowerBinding
    private val detailFlowerModel: DetailFlowerModel by viewModels()

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

        var isClicked = false

        CoroutineScope(Dispatchers.IO).launch {
            val count = detail._id?.let { detailFlowerModel.checkFav(it) }
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        isClicked = true

                        setButtonAttribute(
                            applicationContext,
                            "Hapus Dari Koleksi",
                            R.color.primary_2,
                            R.color.white,
                            4,
                            R.color.primary_2,
                            R.color.primary_2,
                            R.drawable.ic_delete
                        )
                        Log.d("nabil: ", count.toString())
                    } else {
                        isClicked = false

                        setButtonAttribute(
                            applicationContext,
                            "Rawat Bunga",
                            R.color.white,
                            R.color.primary_2,
                            0,
                            R.color.primary_2,
                            R.color.white,
                            R.drawable.ic_bookmark
                        )
                    }
                }
            }
        }

        binding.btnRawat.setOnClickListener {
            isClicked = !isClicked

            if (isClicked) {
                val date = getCurrentDate()
                Log.d("Date", "date: $date")
                detailFlowerModel.addFav(
                    detail._id!!,
                    detail.global_name!!,
                    detail.local_name!!,
                    detail.images!!,
                    date
                )

                setButtonAttribute(
                    this,
                    "Hapus Dari Koleksi",
                    R.color.primary_2,
                    R.color.white,
                    4,
                    R.color.primary_2,
                    R.color.primary_2,
                    R.drawable.ic_delete
                )
            } else {
                detailFlowerModel.deleteFav(detail._id!!)

                setButtonAttribute(
                    this,
                    "Rawat Bunga",
                    R.color.white,
                    R.color.primary_2,
                    0,
                    R.color.primary_2,
                    R.color.white,
                    R.drawable.ic_bookmark
                )
            }

        }
    }

    private fun setButtonAttribute(
        context: Context,
        text: String,
        textColor: Int,
        bgColor: Int,
        strokeWidth: Int,
        strokeColor: Int,
        iconColor: Int,
        iconResource: Int
    ) {
        binding.btnRawat.text = text
        binding.btnRawat.setTextColor(ContextCompat.getColor(context, textColor))
        binding.btnRawat.setBackgroundColor(ContextCompat.getColor(context, bgColor))
        binding.btnRawat.strokeWidth = strokeWidth
        binding.btnRawat.strokeColor = ContextCompat.getColorStateList(context, strokeColor)
        binding.btnRawat.setIconTintResource(iconColor)
        binding.btnRawat.setIconResource(iconResource)
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}
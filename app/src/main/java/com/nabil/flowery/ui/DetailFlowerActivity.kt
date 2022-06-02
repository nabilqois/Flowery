package com.nabil.flowery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nabil.flowery.R
import com.nabil.flowery.response.ListFlower

class DetailFlowerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_flower)

        val detail = intent.getParcelableExtra<ListFlower>(EXTRA_DETAIL) as ListFlower
        Toast.makeText(this, detail.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}
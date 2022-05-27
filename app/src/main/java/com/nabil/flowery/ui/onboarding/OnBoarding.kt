package com.nabil.flowery.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.nabil.flowery.R
import com.nabil.flowery.databinding.ActivityOnBoarding2Binding
import com.nabil.flowery.ui.LoginActivity
import com.nabil.flowery.ui.MainActivity


class OnBoarding : AppCompatActivity() {

    private lateinit var onBoardingAdapter: OnBoardingItemsAdapter
    private lateinit var binding: ActivityOnBoarding2Binding
    private lateinit var indicatorsContainer:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoarding2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setBoardingItems()
        binding.gettingStartedButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun setBoardingItems(){
        onBoardingAdapter = OnBoardingItemsAdapter(
            listOf(
                OnBoardingItems(
                    onBoardingImages = R.drawable.onboard_page1,
                    getString(R.string.onboarding1_text)
                ),
                OnBoardingItems(
                    onBoardingImages = R.drawable.onboard_page2,
                    getString(R.string.onboarding2_text)
                ),
                OnBoardingItems(
                    onBoardingImages = R.drawable.onboard_page3,
                    getString(R.string.onboarding3_text)
                )

            )
        )
        val onboardingViewPager = binding.OnBoardingImages
        onboardingViewPager.adapter = onBoardingAdapter
    }

    private fun setupIndicator()
    {

    }

}
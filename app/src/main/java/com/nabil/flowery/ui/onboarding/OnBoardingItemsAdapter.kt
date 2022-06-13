package com.nabil.flowery.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nabil.flowery.R

class OnBoardingItemsAdapter(private val onboardingItem: List<OnBoardingItems>) :
RecyclerView.Adapter<OnBoardingItemsAdapter.OnBoardingItemViewHolder>()
{
    inner class OnBoardingItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val imageOnBoarding = view.findViewById<ImageView>(R.id.onboard_image_container)
        private val textOnBoarding = view.findViewById<TextView>(R.id.onboard_text_container)

        fun bind(onboardingItem: OnBoardingItems){
            imageOnBoarding.setImageResource(onboardingItem.onBoardingImages)
            textOnBoarding.text = onboardingItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
        return OnBoardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_image,parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        holder.bind(onboardingItem [position])
    }

    override fun getItemCount(): Int {
        return onboardingItem.size
    }
}
package com.nabil.flowery.adapter

import com.nabil.flowery.databinding.ItemFlowerdayBinding
import com.nabil.flowery.response.FlowerDay
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FlowerDayAdapter(private val listFlower: ArrayList<FlowerDay>) : RecyclerView.Adapter<FlowerDayAdapter.ViewHolder>(){
    class ViewHolder(var binding:ItemFlowerdayBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFlowerdayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = listFlower[position]
        holder.binding.tvFlowerName.text = result.global_name
        holder.binding.tvFlowerName2.text = result.scientific_name
        Glide.with(holder.binding.root)
            .load(result.images)
            .into(holder.binding.imgFlower)

    }

    override fun getItemCount(): Int = listFlower.size

    fun setFlowerOfTheDay(flower: List<FlowerDay>) {
        listFlower.clear()
        listFlower.addAll(flower)
        notifyDataSetChanged()
    }
}
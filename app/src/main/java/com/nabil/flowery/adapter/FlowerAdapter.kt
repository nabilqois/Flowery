package com.nabil.flowery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nabil.flowery.databinding.ItemFlowerBinding
import com.nabil.flowery.response.ListFlower

class FlowerAdapter(private val listFlower: ArrayList<ListFlower>) : RecyclerView.Adapter<FlowerAdapter.ViewHolder>(){
    class ViewHolder(var binding: ItemFlowerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFlowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = listFlower[position]
        holder.binding.tvFlowerName.text = result.global_name
        holder.binding.tvFlowerName2.text = result.local_name
        Glide.with(holder.binding.root)
            .load(result.images)
            .into(holder.binding.imgFlower)

    }

    override fun getItemCount(): Int = listFlower.size

    fun setListFlower(flower: List<ListFlower>) {
        listFlower.clear()
        listFlower.addAll(flower)
        notifyDataSetChanged()
    }
}
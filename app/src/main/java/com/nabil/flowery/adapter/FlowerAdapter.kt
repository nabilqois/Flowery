package com.nabil.flowery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nabil.flowery.databinding.ItemFlower2Binding
import com.nabil.flowery.response.ListFlower

class FlowerAdapter(private val listFlower: ArrayList<ListFlower>) : RecyclerView.Adapter<FlowerAdapter.ViewHolder>(){

    class ViewHolder(private val binding: ItemFlower2Binding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(flower: ListFlower) {
            with(binding) {
                tvFlowerName.text = flower.global_name
                tvFlowerName2.text = flower.local_name
                Glide.with(binding.root)
                    .load(flower.images)
                    .into(binding.imgFlower)
                btnFav.setOnClickListener {
                    Toast.makeText(it.context, "Ditekan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallBack

    fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFlower2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFlower[position])
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listFlower[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listFlower.size

    fun setListFlower(flower: List<ListFlower>) {
        listFlower.clear()
        listFlower.addAll(flower)
        notifyDataSetChanged()
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: ListFlower)
    }
}
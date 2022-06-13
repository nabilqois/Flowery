package com.nabil.flowery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nabil.flowery.R
import com.nabil.flowery.databinding.ItemFlower2Binding
import com.nabil.flowery.databinding.ItemFlowerBinding
import com.nabil.flowery.model.DetailFlowerModel
import com.nabil.flowery.response.ListFlower
import com.nabil.flowery.ui.camera.ResultActivity
import java.security.AccessController.getContext

class FlowerAdapter(private val listFlower: ArrayList<ListFlower>) : RecyclerView.Adapter<FlowerAdapter.ViewHolder>(){

    class ViewHolder(private val binding: ItemFlower2Binding) : RecyclerView.ViewHolder(binding.root) {

        val button = binding.btnFav

        fun bind(flower: ListFlower) {
            with(binding) {
                tvFlowerName.text = flower.global_name
                tvFlowerName2.text = flower.local_name
                Glide.with(binding.root)
                    .load(flower.images)
                    .into(binding.imgFlower)
//                btnFav.setOnClickListener {
//                    Toast.makeText(it.context, "Ditekan", Toast.LENGTH_SHORT).show()
//                }

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
//        val result = listFlower[position]
//        holder.binding.tvFlowerName.text = result.global_name
//        holder.binding.tvFlowerName2.text = result.local_name
//        Glide.with(holder.binding.root)
//            .load(result.images)
//            .into(holder.binding.imgFlower)

        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listFlower[holder.adapterPosition])
        }

        holder.button.setOnClickListener {
            onItemClickCallback.onButtonClicked(listFlower[holder.adapterPosition], it)
        }

//        holder.binding.btnFav.setOnClickListener {
//            Toast.makeText(ResultActivity, "Ditekan", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun getItemCount(): Int = listFlower.size

    fun setListFlower(flower: List<ListFlower>) {
        listFlower.clear()
        listFlower.addAll(flower)
        notifyDataSetChanged()
    }

    fun setFavorite(flower: List<ListFlower>) {
        listFlower.clear()
        listFlower.addAll(flower)
        listFlower.reverse()
        notifyDataSetChanged()
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: ListFlower)
        fun onButtonClicked(data: ListFlower, view: View)
    }
}
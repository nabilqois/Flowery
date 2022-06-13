package com.nabil.flowery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nabil.flowery.databinding.ItemTriviaBinding
import com.nabil.flowery.response.ListTrivia

class TriviaAdapter(private val listTrivia: ArrayList<ListTrivia>) : RecyclerView.Adapter<TriviaAdapter.ViewHolder>(){
    class ViewHolder(var binding: ItemTriviaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTriviaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = listTrivia[position]
        holder.binding.tvTriviaName.text = result.content_trivia

    }

    override fun getItemCount(): Int = listTrivia.size

    fun setTrivia(trivia: List<ListTrivia>) {
        listTrivia.clear()
        listTrivia.addAll(trivia)
        notifyDataSetChanged()
    }
}
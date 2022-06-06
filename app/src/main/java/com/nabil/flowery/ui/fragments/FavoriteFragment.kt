package com.nabil.flowery.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nabil.flowery.R
import com.nabil.flowery.adapter.FlowerAdapter
import com.nabil.flowery.database.Favorite
import com.nabil.flowery.databinding.FragmentFavoriteBinding
import com.nabil.flowery.model.FavoriteModel
import com.nabil.flowery.response.ListFlower
import com.nabil.flowery.ui.DetailFlowerActivity

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoriteAdapter: FlowerAdapter
    private val favoriteModel: FavoriteModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecycleView()
        favoriteModel.getAllFav()?.observe(viewLifecycleOwner) {
            val list = mapList(it)
            favoriteAdapter.setListFlower(list)
        }
    }

    private fun mapList(flowers: List<Favorite>) : ArrayList<ListFlower> {
        val listFlower = ArrayList<ListFlower>()
        for (flower in flowers) {
            val flowerMap = ListFlower(
                flower._id,
                flower.global_name,
                flower.local_name,
                flower.images
            )
            listFlower.add(flowerMap)
        }
        return listFlower
    }

    private fun setRecycleView() {
        favoriteAdapter = FlowerAdapter(arrayListOf())
        binding.rvFavoriteList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteAdapter

            favoriteAdapter.setOnItemClickCallback(object : FlowerAdapter.OnItemClickCallBack{
                override fun onItemClicked(data: ListFlower) {
                    showSelectedFlower(data)
                }
            })
        }
    }

    private fun showSelectedFlower(data: ListFlower) {
        val toDetailFlower = Intent(context, DetailFlowerActivity::class.java)
        toDetailFlower.putExtra(DetailFlowerActivity.EXTRA_DETAIL, data)
        startActivity(toDetailFlower)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
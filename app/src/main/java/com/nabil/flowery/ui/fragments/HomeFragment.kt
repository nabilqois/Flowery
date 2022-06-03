package com.nabil.flowery.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nabil.flowery.R
import com.nabil.flowery.adapter.FlowerAdapter
import com.nabil.flowery.adapter.FlowerDayAdapter
import com.nabil.flowery.adapter.TriviaAdapter
import com.nabil.flowery.databinding.FragmentHomeBinding
import com.nabil.flowery.model.FlowerModel
import com.nabil.flowery.model.TriviaModel
import com.nabil.flowery.pref.UserPref
import com.nabil.flowery.response.FlowerDay
import com.nabil.flowery.response.ListFlower
import com.nabil.flowery.response.ListTrivia
import com.nabil.flowery.ui.camera.ResultActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var triviaModel: TriviaModel
    private lateinit var triviaAdapter: TriviaAdapter
    private lateinit var flowerDayAdapter: FlowerDayAdapter
    private lateinit var flowerModel:FlowerModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitleTrivia()
        flowerModel = ViewModelProvider(this)[FlowerModel::class.java]
        flowerDayAdapter = FlowerDayAdapter(arrayListOf())
        triviaModel = ViewModelProvider(this)[TriviaModel::class.java]
        triviaAdapter = TriviaAdapter(arrayListOf())

        binding.rvTriviaList.apply {
            adapter = triviaAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        binding.rvTriviaFlowerOfTheDay.apply {
            adapter = flowerDayAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        setToken()
        triviaModel.listTrivia.observe(viewLifecycleOwner) { listTrivia -> setDescTriviaFlower(listTrivia) }
        flowerModel.listFlower.observe(viewLifecycleOwner) { listFlower -> setFlowerDay(listFlower) }

        binding.layoutSearch.setEndIconOnClickListener {
            val kueri = binding.edtSearch.text.toString()

            actionSearch(kueri)

        }

        binding.edtSearch.setOnEditorActionListener{_, actionId, _ ->
            val kueri = binding.edtSearch.text.toString()

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                actionSearch(kueri)
            }
            true
        }
    }

    private fun actionSearch(query: String) {
        if (query == "") {
            Toast.makeText(context, getString(R.string.do_not_be_empty), Toast.LENGTH_SHORT)
                .show()
        } else {
            val toResultActivity = Intent(activity, ResultActivity::class.java)
            toResultActivity.putExtra("kueri", query)
            toResultActivity.putExtra(ResultActivity.EXTRA_ACTIVITY, "MainActivity")
            startActivity(toResultActivity)
        }
        Log.d("HomeFragment", query)
    }

    private fun setTitleTrivia() {
        binding.tvTriviaTitle.text = getString(R.string.did_you_know)
    }

    private fun setToken() {
        val token = activity?.let { UserPref(it).getResponseLogin() }
        token?.let { triviaModel.getFlowerTrivia(it)}
        token?.let { flowerModel.getFlower(it) }
    }

    private fun setDescTriviaFlower(trivia: List<ListTrivia>) {
        triviaAdapter.setTrivia(trivia)
    }

    private fun setFlowerDay(flower: List<FlowerDay>){
        flowerDayAdapter.setFlowerOfTheDay(flower)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


package com.nabil.flowery.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nabil.flowery.R
import com.nabil.flowery.adapter.TriviaAdapter
import com.nabil.flowery.databinding.FragmentHomeBinding
import com.nabil.flowery.model.TriviaModel
import com.nabil.flowery.pref.UserPref
import com.nabil.flowery.response.ListTrivia
import com.nabil.flowery.ui.camera.ResultActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var triviaModel: TriviaModel
    private lateinit var triviaAdapter: TriviaAdapter

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

        triviaModel = ViewModelProvider(this)[TriviaModel::class.java]
        triviaAdapter = TriviaAdapter(arrayListOf())
        binding.rvTriviaList.apply {
            adapter = triviaAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        setDescTrivia()
        triviaModel.listTrivia.observe(viewLifecycleOwner) { listTrivia -> setDescTriviaFlower(listTrivia) }

        val kueri = binding.edtSearch.text
        binding.layoutSearch.setEndIconOnClickListener {
            if (kueri!!.isEmpty()) {
                Toast.makeText(context, getString(R.string.do_not_be_empty), Toast.LENGTH_SHORT)
                    .show()
            } else {
//                Toast.makeText(context, kueri, Toast.LENGTH_SHORT).show()
            }
            Log.d("HomeFragment", kueri.toString())

            val toResultActivity = Intent(activity, ResultActivity::class.java)
            toResultActivity.putExtra("kueri", kueri.toString())
            toResultActivity.putExtra(ResultActivity.EXTRA_ACTIVITY, "MainActivity")
            startActivity(toResultActivity)
        }
    }


    private fun setTitleTrivia() {
        binding.tvTriviaTitle.text = getString(R.string.did_you_know)
    }

    private fun setDescTrivia() {
        val token = activity?.let { UserPref(it).getResponseLogin() }
        token?.let { triviaModel.getFlowerTrivia(it)}
    }

    private fun setDescTriviaFlower(trivia: List<ListTrivia>) {
        triviaAdapter.setTrivia(trivia)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


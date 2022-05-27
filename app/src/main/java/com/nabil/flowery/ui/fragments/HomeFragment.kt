package com.nabil.flowery.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.nabil.flowery.R
import com.nabil.flowery.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitleTrivia()

        var search = 0
        var text1 = binding.edtSearch.text
        binding.edtSearch.doOnTextChanged { _, _, _, count ->
            search = count
        }

        binding.layoutSearch.setEndIconOnClickListener {
            if (search == 0) {
                Toast.makeText(context, text1, Toast.LENGTH_SHORT).show()
            } else if (search > 1){
                Toast.makeText(context, text1, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setTitleTrivia() {
        binding.tvTriviaTitle.text = "Tahukah Kamu ?"
    }

    private fun setDescTrivia() {
        binding.tvTriviaDesc.text = "Bunga mawar memiliki ..."
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
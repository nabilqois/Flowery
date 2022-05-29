package com.nabil.flowery.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nabil.flowery.databinding.FragmentHomeBinding
import com.nabil.flowery.ui.camera.ResultActivity

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

        val kueri = binding.edtSearch.text
        binding.layoutSearch.setEndIconOnClickListener {
            if (kueri!!.isEmpty()) {
                Toast.makeText(context, "Jangan kosong", Toast.LENGTH_SHORT).show()
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
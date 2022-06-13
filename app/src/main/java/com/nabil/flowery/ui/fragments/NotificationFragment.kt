package com.nabil.flowery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nabil.flowery.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       binding.addButton.setOnClickListener{
           changeFragment(functionFragment)
       }

    }

    private val functionFragment by lazy {
        NotificationFunctionFragment()
    }

    private fun changeFragment(fragment:Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(com.nabil.flowery.R.id.fragment_container, fragment)
        transaction.commit()
    }






}
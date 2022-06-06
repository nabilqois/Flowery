package com.nabil.flowery.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nabil.flowery.databinding.FragmentProfileBinding
import com.nabil.flowery.pref.UserPref
import com.nabil.flowery.ui.LoginActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setProfileName()

        binding.btnLogout.setOnClickListener {
            UserPref(requireContext()).logout()
            val toLoginActivity = Intent(context, LoginActivity::class.java)
            startActivity(toLoginActivity)
            activity?.finish()

        }
    }

    private fun setProfileName(){
        val name = activity?.let { UserPref(it).getResponseUserName() }
        binding.profileName.text = name.toString()
    }

}
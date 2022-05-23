package com.nabil.flowery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nabil.flowery.R
import com.nabil.flowery.databinding.ActivityMainBinding
import com.nabil.flowery.ui.fragments.FavoriteFragment
import com.nabil.flowery.ui.fragments.HomeFragment
import com.nabil.flowery.ui.fragments.NotificationFragment
import com.nabil.flowery.ui.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val homeFragment by lazy {
        HomeFragment()
    }

    private val favoriteFragment by lazy {
        FavoriteFragment()
    }

    private val notificationFragment by lazy {
        NotificationFragment()
    }

    private val profileFragment by lazy {
        ProfileFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.background = null

        changeFragment(homeFragment)

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> changeFragment(homeFragment)
                R.id.menu_favorite -> changeFragment(favoriteFragment)
                R.id.menu_notification -> changeFragment(notificationFragment)
                R.id.menu_profile -> changeFragment(profileFragment)
            }

            if (it.itemId == R.id.menu_home) showFab() else hideFab()

            true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    private fun hideFab() {
        binding.fabCamera.hide()
    }

    private fun showFab() {
        binding.fabCamera.show()
    }
}
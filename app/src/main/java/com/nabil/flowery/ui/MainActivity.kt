package com.nabil.flowery.ui

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nabil.flowery.R
import com.nabil.flowery.databinding.ActivityMainBinding
import com.nabil.flowery.ui.camera.CameraActivity
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
        createNotificationChannel()


        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        binding.bottomNav.background = null

        changeFragment(homeFragment)

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> changeFragment(homeFragment)
                R.id.menu_favorite -> changeFragment(favoriteFragment)
                R.id.menu_notification -> changeFragment(notificationFragment)
                R.id.menu_profile -> changeFragment(profileFragment)
            }

            if (it.itemId != R.id.menu_home) showFab(false) else showFab(true)

            true
        }

        binding.fabCamera.setOnClickListener { startCameraX() }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
    private fun createNotificationChannel(){
        val name = "Channel"
        val desc = "Desc"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("channel1",name,importance)
        channel.description = desc
        val notificationManager = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    private fun showFab(state: Boolean) {
        if (state) binding.fabCamera.show() else binding.fabCamera.hide()
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}
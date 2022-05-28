package com.nabil.flowery.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.nabil.flowery.R
import com.nabil.flowery.databinding.ActivityLoginBinding
import com.nabil.flowery.model.AuthModel
import com.nabil.flowery.pref.UserPref


class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    private val authModel: AuthModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val email = binding.emailBox.text.toString()
            val password = binding.passBox.text.toString()

            if (email.isEmpty()) {
                binding.emailBox.error = "Email wajib diisi"
            } else if (password.isEmpty()) {
                binding.passBox.error = "Password wajib diisi"
            }

            authModel.getLoginData(email, password)

            authModel.isError.observe(this) { isError ->
                if (isError) {
                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                } else {
                    val loginResponse = authModel.loginData.value
                    UserPref(this).setResponseLogin(loginResponse!!)
                    val toMainActivity = Intent(this, MainActivity::class.java)
                    toMainActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(toMainActivity)
                    finish()
                }
            }
        }

    }


}
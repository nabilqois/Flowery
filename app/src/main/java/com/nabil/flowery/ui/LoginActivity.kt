package com.nabil.flowery.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
                binding.emailBox.error = getString(R.string.email_here)
            } else if (password.isEmpty()) {
                binding.passBox.error = getString(R.string.password_here)
            }

            authModel.getLoginData(email, password)

            authModel.isError.observe(this) { isError ->
                if (isError) {
                    Toast.makeText(this, getString(R.string.mistake_found), Toast.LENGTH_SHORT).show()
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

        binding.registerButton.setOnClickListener{
            val toRegisterActivity = Intent(this, RegisterActivity::class.java)
            toRegisterActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(toRegisterActivity)
            finish()
        }

    }


}
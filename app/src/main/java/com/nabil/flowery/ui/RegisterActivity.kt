package com.nabil.flowery.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nabil.flowery.databinding.ActivityRegisterBinding
import com.nabil.flowery.model.AuthModelRegis

class RegisterActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRegisterBinding
    private lateinit var regView: AuthModelRegis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        regView = ViewModelProvider(
            this,ViewModelProvider
                .NewInstanceFactory())[AuthModelRegis::class.java]


        binding.registrationButton.setOnClickListener{
            val name = binding.nameBox.text.toString()
            val email = binding.emailBox.text.toString()
            val password = binding.passBox.text.toString()

            when {

                name.isEmpty() || email.isEmpty() || password.isEmpty() -> showMessage("Please fill in the blanks!")

                password.length < 6 -> showMessage("Password must be more than 6 letters!")

                else -> {
                    regView.getRegisterData(name, email, password)
                    showMessage("Please Login!")
                    val toLoginActivity = Intent(this, LoginActivity::class.java)
                    toLoginActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(toLoginActivity)
                    finish()
                }
            }
        }

        binding.loginButtonInRegister.setOnClickListener{
            showMessage("Please Login with your existing account!")
            val toLoginActivity = Intent(this, LoginActivity::class.java)
            toLoginActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(toLoginActivity)
            finish()
        }

    }

    private fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
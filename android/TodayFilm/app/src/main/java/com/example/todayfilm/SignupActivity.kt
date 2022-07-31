package com.example.todayfilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todayfilm.databinding.ActivityLoginBinding
import com.example.todayfilm.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupToLogin.setOnClickListener {
            clickableFalse()
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    private fun clickableFalse() {
        val binding = ActivitySignupBinding.inflate(layoutInflater)
        binding.signupBtn.isClickable = false
        binding.signupToLogin.isClickable = false
        binding.signupId.isClickable = false
        binding.signupPw.isClickable = false
        binding.signupPwCheck.isClickable = false
    }
}
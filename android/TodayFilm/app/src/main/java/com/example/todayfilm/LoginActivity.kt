package com.example.todayfilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todayfilm.databinding.ActivityIntroBinding
import com.example.todayfilm.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginToSignup.setOnClickListener {
            clickableFalse()
            val intent = Intent(this, SignupActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    private fun clickableFalse() {
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.loginBtn.isClickable = false
        binding.loginToSignup.isClickable = false
        binding.loginId.isClickable = false
        binding.loginPw.isClickable = false
    }
}
package com.example.todayfilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.todayfilm.databinding.ActivityIntroBinding
import com.example.todayfilm.databinding.ActivityLoginBinding

class IntroActivity : AppCompatActivity() {
    val binding by lazy{ ActivityIntroBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.LoginBtn.setOnClickListener{
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@IntroActivity, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }, 10)
        }

        binding.SignupBtn.setOnClickListener{
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@IntroActivity, SignupActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }, 10)
        }

        binding.StartBtn.setOnClickListener{
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@IntroActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }, 10)
        }
    }
}
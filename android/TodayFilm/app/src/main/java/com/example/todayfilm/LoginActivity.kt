package com.example.todayfilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.todayfilm.data.LoginData
import com.example.todayfilm.data.User
import com.example.todayfilm.databinding.ActivityLoginBinding
import com.example.todayfilm.retrofit.NetWorkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    val binding by lazy{ ActivityLoginBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginToSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.loginBtn.setOnClickListener{
            var LoginId = binding.loginId.text.toString()
            var LoginPw = binding.loginPw.text.toString()
            var user = User()
            user.id = LoginId
            user.pw = LoginPw

            val call = NetWorkClient.GetNetwork.login(user)
            call.enqueue(object : Callback<LoginData> {
                override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                    val result: LoginData? = response.body()

                    if (result?.message == "성공"){
                        Handler(Looper.getMainLooper()).postDelayed({
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            finish()
                        }, 500)
                    }else{
                        binding.loginIdErr.setText("ID가 존재하지 않거나 PW가 틀렸습니다.")
                    }

                }

                override fun onFailure(call: Call<LoginData>, t: Throwable) {
                    Log.d("", "실패"+t.message.toString())
                }
            })


        }

    }
}
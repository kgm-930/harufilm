package com.example.todayfilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.todayfilm.data.SignupData
import com.example.todayfilm.data.User
import com.example.todayfilm.databinding.ActivitySignupBinding
import com.example.todayfilm.retrofit.NetWorkClient.GetNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    val binding by lazy{ ActivitySignupBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signup.setOnClickListener {
            val id = binding.SignpId.text.toString()
            val pw = binding.SignpPw.text.toString()
            val pw2 = binding.SignpPw2.text.toString()
            if (pw==pw2) {
                var user = User()
                user.id = id
                user.pw = pw
                val call = GetNetwork.signUp(user)
                call.enqueue(object : Callback<SignupData> {
                    override fun onResponse(call: Call<SignupData>, response: Response<SignupData>) {
                        val result:SignupData? = response.body()
                        Log.d("결과","성공" + result?.result )
                        if (result?.message == "성공") {
                            var dialog = AlertDialog.Builder(this@SignupActivity)
                            dialog.setTitle("회원가입 성공!")
                            dialog.setMessage("회원가입이 정상적으로 완료되었습니다.")
                            dialog.show()

                            Handler(Looper.getMainLooper()).postDelayed({
                                val intent = Intent(this@SignupActivity, MainActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(intent)
                                finish()
                            }, 2000)
                        }
                    }

                    override fun onFailure(call: Call<SignupData>, t: Throwable) {
                        Log.d("", "실패"+t.message.toString())
                    }
                })
            }
        }
    }
}
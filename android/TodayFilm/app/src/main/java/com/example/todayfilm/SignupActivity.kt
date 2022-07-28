package com.example.todayfilm

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.todayfilm.data.SignupData
import com.example.todayfilm.data.User
import com.example.todayfilm.databinding.ActivitySignupBinding
import com.example.todayfilm.retrofit.NetWorkClient.GetNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    val binding by lazy { ActivitySignupBinding.inflate(layoutInflater) }
    override fun onBackPressed() {
        val intent = Intent(this@SignupActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        binding.signupToLogin.setOnClickListener {
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.signupBtn.setOnClickListener {
            val id = binding.signupId.text.toString()
            val pw = binding.signupPw.text.toString()
            val pw2 = binding.signupPwCheck.text.toString()

            if ((id.length == 0) || (pw.length == 0) || (pw2.length == 0)) {
                binding.signupIdErr.setText("기입하지 않은 란이 있습니다.")
            } else if (pw == pw2) {
                var user = User()
                user.id = id
                user.pw = pw
                val call = GetNetwork.signUp(user)
                call.enqueue(object : Callback<SignupData> {
                    override fun onResponse(
                        call: Call<SignupData>,
                        response: Response<SignupData>
                    ) {
                        val result: SignupData? = response.body()
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
                        } else {
                            binding.signupIdErr.setText("이미 존재하는 아이디 입니다.")
                        }
                    }

                    override fun onFailure(call: Call<SignupData>, t: Throwable) {
                        Log.d("", "실패" + t.message.toString())
                    }
                })
            } else {
                binding.signupPwCheckErr.setText("비밀번호가 일치하지 않습니다.")
            }
        }
    }
}
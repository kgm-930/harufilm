package com.example.todayfilm

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.todayfilm.data.SignupData
import com.example.todayfilm.data.User
import com.example.todayfilm.databinding.ActivitySignupBinding
import com.example.todayfilm.retrofit.NetWorkClient.GetNetwork
import kotlinx.android.synthetic.main.activity_settings.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private var doubleBackToExit = false
    val binding by lazy { ActivitySignupBinding.inflate(layoutInflater) }

    override fun onBackPressed() {
        if (doubleBackToExit) {
            finishAffinity()
        } else {
            Toast.makeText(this, "종료하시려면 뒤로가기를 한번 더 눌러주세요.", Toast.LENGTH_SHORT).show()
            doubleBackToExit = true
            runDelayed(1500L) {
                doubleBackToExit = false
            }
        }
    }

    fun runDelayed(millis: Long, function: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(function, millis)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val spinner = binding.signupQuestions

        val arrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.questions,
            R.layout.spinner_item
        )

        spinner.adapter = arrayAdapter

        binding.signupToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            clickableFalse()
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

        binding.signupBtn.setOnClickListener {
            val id = binding.signupId.text.toString()
            val pw = binding.signupPw.text.toString()
            val pw2 = binding.signupPwCheck.text.toString()
            val nickname = binding.signupUsername.text.toString()
            val question = binding.signupQuestions.selectedItemPosition
            val answer = binding.signupAnswer.text.toString()

            if ((id.length == 0) || (pw.length == 0) || (pw2.length == 0)) {
                binding.signupErr.setText("기입하지 않은 란이 있습니다.")
            } else if (pw == pw2) {
                var user = User()
                user.userid = id
                user.userpassword = pw
                user.usernickname = nickname
                user.useranswer = answer
                val call = GetNetwork.signUp(user)
                call.enqueue(object : Callback<SignupData> {
                    override fun onResponse(
                        call: Call<SignupData>,
                        response: Response<SignupData>
                    ) {
                        val result: SignupData? = response.body()
                        if (result?.message == "계정 생성이 완료되었습니다.") {
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
                            binding.signupErr.setText("이미 존재하는 아이디 입니다.")
                        }
                    }

                    override fun onFailure(call: Call<SignupData>, t: Throwable) {
                        Log.d("", "실패" + t.message.toString())
                    }
                })
            } else {
                binding.signupErr.setText("비밀번호가 일치하지 않습니다.")
            }
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
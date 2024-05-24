package com.sopt.now.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.ServicePool
import com.sopt.now.compose.databinding.ActivityLoginBinding
import com.sopt.now.dataClass.RequestLogInDto
import com.sopt.now.dataClass.ResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val authService by lazy { ServicePool.authService }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.loginBtn.setOnClickListener {
            login()
        }
        binding.goSignupBtn.setOnClickListener {
            val intentSignup = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intentSignup)
        }
    }


    private fun login() {
        val id = binding.editTextId.text.toString()
        val password = binding.editTextPw.text.toString()
        val loginRequest = RequestLogInDto(id, password)

        authService.login(loginRequest).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(
                call: Call<ResponseDto>,
                response: Response<ResponseDto>
            ) {
                if (response.isSuccessful) {
                    val memberId = response.headers()["Location"]!!.toInt()
                    Toast.makeText(
                        this@LoginActivity,
                        "로그인 성공 memberID: $memberId",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intentHome = Intent(this@LoginActivity, HomeActivity::class.java).apply {
                        putExtra("memberId", memberId)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intentHome)
                    Log.d("LoginActivity", "put $memberId to HomeActivity")

                } else {
                    Toast.makeText(this@LoginActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "로그인 요청 실패: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}

//class LoginActivity : AppCompatActivity() {
//
//
//    private lateinit var binding: ActivityLoginBinding
//    private val nameId = "ID"
//    private val namePassword = "PASSWORD"
//    private val nameNickname = "NICKNAME"
//    private val nameMbti = "MBTI"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val id = intent.getStringExtra(nameId)
//        val password = intent.getStringExtra(namePassword)
//        val nickname = intent.getStringExtra(nameNickname)
//        val mbti = intent.getStringExtra(nameMbti)
//
//
//        binding.loginBtn.setOnClickListener {
//            val enteredId = binding.editTextId.text.toString()
//            val enteredPassword = binding.editTextPw.text.toString()
//
//            when {
//                enteredId != id -> {
//                    Snackbar.make(
//                        binding.root,
//                        "아이디를 확인하세요.",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                }
//
//                enteredPassword != password -> {
//                    Snackbar.make(
//                        binding.root,
//                        "비밀번호를 확인하세요.",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                }
//
//                else -> {
//                    Log.d("Login Success", "Login Success")
//                    val intentHome = Intent(this, HomeActivity::class.java).apply {
//                        putExtra(nameId, id)
//                        putExtra(namePassword, password)
//                        putExtra(nameNickname, nickname)
//                        putExtra(nameMbti, mbti)
//                    }
//                    Snackbar.make(
//                        binding.root,
//                        "로그인 성공!",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                    startActivity(intentHome)
//
//                }
//            }
//
//        }
//        binding.goSignupBtn.setOnClickListener {
//            val intentSignup = Intent(this, SignUpActivity::class.java)
//            startActivity(intentSignup)
//        }
//
//    }
//}
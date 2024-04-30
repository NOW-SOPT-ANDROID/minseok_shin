package com.sopt.now.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.ServicePool
import com.sopt.now.dataClass.RequestInfoDto
import com.sopt.now.dataClass.RequestLogInDto
import com.sopt.now.dataClass.ResponseInfoDto
import com.sopt.now.dataClass.ResponseLogInDto
import com.sopt.now.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val authService by lazy { ServicePool.authService }

    private val nameId = "ID"
    private val nameNickname = "NICKNAME"
    private val namePhone = "PHONE"

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

    private fun searchInfo(id: String?) {
        val infoRequest = RequestInfoDto(id!!)
        authService.info(infoRequest).enqueue(object : Callback<ResponseInfoDto> {
            override fun onResponse(
                call: Call<ResponseInfoDto>,
                response: Response<ResponseInfoDto>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(this@LoginActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                    Log.d("LoginActivity", "Response successful: ${response.body()}")
                    val userInfo = response.body()!!.data
                    val intentHome = Intent(this@LoginActivity, HomeActivity::class.java).apply {
                        putExtra(nameId, userInfo?.authenticationId)
                        putExtra(nameNickname, userInfo?.nickname)
                        putExtra(namePhone, userInfo?.phone)
                    }
                    startActivity(intentHome)

                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "사용자 정보 조회 실패 ${response.body()?.code}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

            override fun onFailure(call: Call<ResponseInfoDto>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "조회 요청 실패: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }


    private fun login() {
        val id = binding.editTextId.text.toString()
        val password = binding.editTextPw.text.toString()
        val loginRequest = RequestLogInDto(id, password)

        authService.login(loginRequest).enqueue(object : Callback<ResponseLogInDto> {
            override fun onResponse(
                call: Call<ResponseLogInDto>,
                response: Response<ResponseLogInDto>
            ) {
                if (response.isSuccessful) {
                    val memberId = response.headers()["Location"]
//                    Toast.makeText(
//                        this@LoginActivity,
//                        "로그인 성공 memberID: $memberId",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    searchInfo(memberId)
                } else {
                    Toast.makeText(this@LoginActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseLogInDto>, t: Throwable) {
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
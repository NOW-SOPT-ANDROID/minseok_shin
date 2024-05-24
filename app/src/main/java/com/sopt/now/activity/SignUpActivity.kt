package com.sopt.now.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.ServicePool
import com.sopt.now.compose.databinding.ActivitySignUpBinding
import com.sopt.now.dataClass.RequestSignUpDto
import com.sopt.now.dataClass.ResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val authService by lazy { ServicePool.authService }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.signupBtn.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val signUpRequest = getSignUpRequestDto()
        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(
                call: Call<ResponseDto>,
                response: Response<ResponseDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseDto? = response.body()
                    val userId = response.headers()["location"]
                    Toast.makeText(
                        this@SignUpActivity,
                        "회원가입 성공 유저의 ID는 $userId 입니둥",
                        Toast.LENGTH_SHORT,
                    ).show()
                    Log.d("SignUp", "data: $data, userId: $userId")
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    val errorC = response.code()
                    val errorM = response.message()
                    Toast.makeText(
                        this@SignUpActivity,
                        "회원가입 실패 $errorC , $errorM",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                Toast.makeText(this@SignUpActivity, "서버 에러 발생 ", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        val id = binding.editTextId.text.toString()
        val password = binding.editTextPw.text.toString()
        val nickname = binding.editTextId.text.toString()
        val phoneNumber = binding.editTextPhone.text.toString()
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phoneNumber
        )
    }
}

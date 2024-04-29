package com.sopt.now.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.ServicePool
import com.sopt.now.dataClass.RequestSignUpDto
import com.sopt.now.dataClass.ResponseSignUpDto
import com.sopt.now.databinding.ActivitySignUpBinding
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
        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseSignUpDto? = response.body()
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

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
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

//class SignUpActivity : AppCompatActivity() {
//    private lateinit var binding: ActivitySignUpBinding
//    private val nameId="ID"
//    private val namePassword = "PASSWORD"
//    private val nameNickname = "NICKNAME"
//    private val nameMbti = "MBTI"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySignUpBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        binding.signupBtn.setOnClickListener {
//            val mbtiInput = binding.editTextMbti.text.toString().uppercase()
//
//            val id = binding.editTextId.text.toString()
//            val password = binding.editTextPw.text.toString()
//            val nickname = binding.editTextNn.text.toString()
//            val mbti = binding.editTextMbti.text.toString().uppercase()
//
//            when {
//                id.length !in 6..10 -> {
//                    Snackbar.make(
//                        binding.root,
//                        "ID는 6~10글자여야 합니다.",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                }
//                password.length !in 8..12 -> {
//                    Snackbar.make(
//                        binding.root,
//                        "Password는 8~12글자여야 합니다.",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                }
//                nickname.isEmpty() -> {
//                    Snackbar.make(
//                        binding.root,
//                        "닉네임은 한 글자 이상이어야 합니다.",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                }
//                mbtiInput.length != 4 || !validMBTI(mbtiInput) -> {
//                    Snackbar.make(
//                        binding.root,
//                        "MBTI 형식이 올바르지 않습니다. (E/I, S/N, F/T, P/J)",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                }
//                else -> {
//                    val intent = Intent(this, LoginActivity::class.java).apply {
//                        putExtra(nameId, id)
//                        putExtra(namePassword, password)
//                        putExtra(nameNickname, nickname)
//                        putExtra(nameMbti, mbti)
//                    }
//                    Snackbar.make(
//                        binding.root,
//                        "회원가입 성공",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                    startActivity(intent)
//                }
//            }
//        }
//    }
//}
//
//fun validMBTI(mbti:String):Boolean{
//    val validMBTIPattern = Regex("[EI][SN][TF][JP]")
//    return validMBTIPattern.matches(mbti)
//}

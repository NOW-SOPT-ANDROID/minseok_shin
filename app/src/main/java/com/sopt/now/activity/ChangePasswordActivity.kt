package com.sopt.now.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.ServicePool
import com.sopt.now.compose.databinding.ActivityChangePasswordBinding
import com.sopt.now.dataClass.RequestPasswordDto
import com.sopt.now.dataClass.ResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {

    private val binding by lazy { ActivityChangePasswordBinding.inflate(layoutInflater) }
    private val authService by lazy { ServicePool.authService }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("ChangePassword", "view onCreate")
        initViews()
    }

    private fun initViews() {
        val memberId = intent.getIntExtra("memberId", 0)

        binding.changePasswordBtn.setOnClickListener {
            changePassword(memberId)
        }
    }

    private fun getPasswordRequestDto(): RequestPasswordDto {
        val previousPw = binding.editTextPreviousPw.text.toString()
        val newPw = binding.editTextNewPw.text.toString()
        val newPwCheck = binding.editTextPwCheck.text.toString()
        return RequestPasswordDto(
            previousPassword = previousPw,
            newPassword = newPw,
            newPasswordVerification = newPwCheck,
        )
    }

    private fun changePassword(memberId: Int) {
        val passwordRequest = getPasswordRequestDto()
        authService.changePassword(memberId, passwordRequest)
            .enqueue(object : Callback<ResponseDto> {
                override fun onResponse(
                    call: Call<ResponseDto>,
                    response: Response<ResponseDto>,
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@ChangePasswordActivity,
                            "비밀번호 변경 완료! 새 비밀번호로 로그인 하세요",
                            Toast.LENGTH_SHORT,
                        ).show()

                        val intent = Intent(this@ChangePasswordActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        val errorC = response.code()
                        val errorM = response.message()
                        Toast.makeText(
                            this@ChangePasswordActivity,
                            "비밀번호 변경 실패 memberId: $memberId,  $errorC , $errorM",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                    Toast.makeText(this@ChangePasswordActivity, "서버 에러 발생 ", Toast.LENGTH_SHORT)
                        .show()
                }
            })
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

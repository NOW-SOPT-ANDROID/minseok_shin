package com.sopt.now.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val nameId="ID"
    private val namePassword = "PASSWORD"
    private val nameNickname = "NICKNAME"
    private val nameMbti = "MBTI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signupBtn.setOnClickListener {
            val mbtiInput = binding.editTextMbti.text.toString().uppercase()

            val id = binding.editTextId.text.toString()
            val password = binding.editTextPw.text.toString()
            val nickname = binding.editTextNn.text.toString()
            val mbti = binding.editTextMbti.text.toString().uppercase()

            when {
                id.length !in 6..10 -> {
                    Snackbar.make(
                        binding.root,
                        "ID는 6~10글자여야 합니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                password.length !in 8..12 -> {
                    Snackbar.make(
                        binding.root,
                        "Password는 8~12글자여야 합니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                nickname.isEmpty() -> {
                    Snackbar.make(
                        binding.root,
                        "닉네임은 한 글자 이상이어야 합니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                mbtiInput.length != 4 || !validMBTI(mbtiInput) -> {
                    Snackbar.make(
                        binding.root,
                        "MBTI 형식이 올바르지 않습니다. (E/I, S/N, F/T, P/J)",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val intent = Intent(this, LoginActivity::class.java).apply {
                        putExtra(nameId, id)
                        putExtra(namePassword, password)
                        putExtra(nameNickname, nickname)
                        putExtra(nameMbti, mbti)
                    }
                    Snackbar.make(
                        binding.root,
                        "회원가입 성공",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    startActivity(intent)
                }
            }
        }
    }
}

fun validMBTI(mbti:String):Boolean{
    val validMBTIPattern = Regex("[EI][SN][TF][JP]")
    return validMBTIPattern.matches(mbti)
}

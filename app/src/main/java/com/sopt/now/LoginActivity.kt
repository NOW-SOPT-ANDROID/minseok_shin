package com.sopt.now


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val nameId="ID"
    private val namePassword = "PASSWORD"
    private val nameNickname = "NICKNAME"
    private val nameMbti = "MBTI"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(nameId)
        val password = intent.getStringExtra(namePassword)
        val nickname = intent.getStringExtra(nameNickname)
        val mbti = intent.getStringExtra(nameMbti)


        binding.loginBtn.setOnClickListener {
            val enteredId = binding.editTextId.text.toString()
            val enteredPassword = binding.editTextPw.text.toString()

            when {
                enteredId != id -> {
                    Snackbar.make(
                        binding.root,
                        "아이디를 확인하세요.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                enteredPassword != password -> {
                    Snackbar.make(
                        binding.root,
                        "비밀번호를 확인하세요.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val intentHome = Intent(this, HomeActivity::class.java).apply {
                        putExtra("ID", id)
                        putExtra("PASSWORD", password)
                        putExtra("NICKNAME", nickname)
                        putExtra("MBTI", mbti)
                    }
                    startActivity(intentHome)
                }
            }

        }
        binding.goSignupBtn.setOnClickListener {
            val intentSignup = Intent(this, SignUpActivity::class.java)
            startActivity(intentSignup)
        }

    }
}
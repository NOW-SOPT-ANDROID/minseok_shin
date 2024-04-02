package com.sopt.now


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("ID")
        val password = intent.getStringExtra("PASSWORD")
        val nickname = intent.getStringExtra("NICKNAME")
        val mbti = intent.getStringExtra("MBTI")


        binding.loginBtn.setOnClickListener() {
            val enteredId = binding.editTextId.text.toString()
            val enteredPassword = binding.editTextPw.text.toString()

            if (enteredId == id) {
                if (enteredPassword == password) {
                    var intent_main = Intent(this, MainActivity::class.java).apply {
                        putExtra("ID", id)
                        putExtra("PASSWORD", password)
                        putExtra("NICKNAME", nickname)
                        putExtra("MBTI", mbti)
                    }
                    startActivity(intent_main)

                } else {
                    Snackbar.make(
                        binding.root,
                        "비밀번호를 확인하세요.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            } else {
                Snackbar.make(
                    binding.root,
                    "아이디를 확인하세요.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }


        binding.goSignupBtn.setOnClickListener() {
            var intent_signup = Intent(this, SignUpActivity::class.java)
            startActivity(intent_signup)
        }




    }
}
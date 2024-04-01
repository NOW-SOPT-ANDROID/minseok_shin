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


        binding.loginBtn.setOnClickListener() {
            var intent = Intent(this, MainActivity::class.java)

            if (binding.editTextId.length() < 6) {

                Snackbar.make(
                    binding.root,
                    "아이디를 확인하세요.",
                    Snackbar.LENGTH_SHORT
                ).show()

            } else {
                if (binding.editTextPw.length() in 6..10) {
                    startActivity(intent)

                } else {
                    Snackbar.make(
                        binding.root,
                        "비밀번호를 확인하세요.",
                        Snackbar.LENGTH_SHORT
                    ).show()

                }
            }
        }


    }
}
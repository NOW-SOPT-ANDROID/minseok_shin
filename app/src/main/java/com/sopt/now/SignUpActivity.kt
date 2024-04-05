package com.sopt.now


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignUpBinding
import java.util.Locale

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signupBtn.setOnClickListener() {
            var intent = Intent(this, LoginActivity::class.java)
            val mbtiInput = binding.editTextMbti.text.toString().toUpperCase(Locale.ROOT) // Convert to uppercase for case-insensitive comparison
            val validMBTIPattern = Regex("[EI][SN][TF][JP]")

            val id = binding.editTextId.text.toString()
            val password = binding.editTextPw.text.toString()
            val nickname = binding.editTextNn.text.toString()
            val mbti = binding.editTextMbti.text.toString().toUpperCase(Locale.ROOT)

            if (id.length in 6..10) {
                if (password.length in 8..12) {
                    if (nickname.isNotEmpty()) {
                        if (mbtiInput.length == 4 && validMBTIPattern.matches(mbtiInput)) {
                            val intent = Intent(this, LoginActivity::class.java).apply {
                                putExtra("ID", id)
                                putExtra("PASSWORD", password)
                                putExtra("NICKNAME", nickname)
                                putExtra("MBTI", mbti)
                            }
                            Snackbar.make(
                                binding.root,
                                "회원가입 성공",
                                Snackbar.LENGTH_SHORT
                            ).show()
                            startActivity(intent)
                        } else {
                            Snackbar.make(
                                binding.root,
                                "MBTI 형식이 올바르지 않습니다. (E/I, S/N, F/T, P/J)",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Snackbar.make(
                            binding.root,
                            "닉네임은 한 글자 이상이어야 합니다.",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Snackbar.make(
                        binding.root,
                        "Password는 8~12글자여야 합니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            } else {
                Snackbar.make(
                    binding.root,
                    "ID는 6~10글자여야 합니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

    }

}

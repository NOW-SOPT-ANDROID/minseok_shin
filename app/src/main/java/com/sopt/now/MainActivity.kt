package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //TODO: inflate 두 번 하면 오류
        setContentView(binding.root)

        // Receive the passed information from the intent extras
        val id = intent.getStringExtra("ID")
        val password = intent.getStringExtra("PASSWORD")
        val nickname = intent.getStringExtra("NICKNAME")
        val mbti = intent.getStringExtra("MBTI")

        Snackbar.make(
            binding.root,
            "로그인 성공!",
            Snackbar.LENGTH_SHORT
        ).show()


        binding.textNickname.text = "닉네임: $nickname"
        binding.textMbti.text = "MBTI: $mbti"
        binding.textId.text="$id"
        binding.textPw.text="$password"



        val mbtiImageName = mbti?.toLowerCase() + ".png"
        val mbtiImageId = mbtiImageName?.let { resources.getIdentifier(it, "drawable", packageName) }
        if (mbtiImageId != null && mbtiImageId != 0) {
            binding.imageMbti.setImageResource(mbtiImageId)

        }

        // Assume 'mbtiType' is the MBTI type you have and it's assigned somewhere in your code

// Map MBTI types to their corresponding description resource names
        val mbtiDescriptionMap = mapOf(
            "ISFP" to R.string.isfp,
            "ISFJ" to R.string.isfj,
            "ISTP" to R.string.istp,
            "ISTJ" to R.string.istj,
            "INFP" to R.string.infp,
            "INFJ" to R.string.infj,
            "INTP" to R.string.intp,
            "INTJ" to R.string.intj,
            "ESFP" to R.string.esfp,
            "ESFJ" to R.string.esfj,
            "ESTP" to R.string.estp,
            "ESTJ" to R.string.estj,
            "ENFP" to R.string.enfp,
            "ENFJ" to R.string.enfj,
            "ENTP" to R.string.entp,
            "ENTJ" to R.string.entj

        )
        // Retrieve the description resource ID based on the MBTI type
        val mbtiDescriptionResId = mbti?.let { mbtiDescriptionMap[it] }

        // Get the description string using the resource ID
        val mbtiDescription = mbtiDescriptionResId?.let { getString(it) } ?: getString(R.string.default_description)

        // Set the description using data binding
        binding.textMbtiDes.text = mbtiDescription

    }
}
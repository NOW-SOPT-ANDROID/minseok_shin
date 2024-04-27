package com.sopt.now.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.R
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val nameId="ID"
    private val namePassword = "PASSWORD"
    private val nameNickname = "NICKNAME"
    private val nameMbti = "MBTI"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //TODO: inflate 두 번 하면 오류 공부
        setContentView(binding.root)



        val id = intent.getStringExtra(nameId)
        val password = intent.getStringExtra(namePassword)
        val nickname = intent.getStringExtra(nameNickname)
        val mbti = intent.getStringExtra(nameMbti)

        Snackbar.make(
            binding.root,
            "로그인 성공!",
            Snackbar.LENGTH_SHORT
        ).show()


        binding.textNickname.text = getString(R.string.nickname,nickname)
        binding.textMbti.text = getString(R.string.mbti,mbti)
        binding.textId.text="$id"
        binding.textPw.text="$password"



        val mbtiImageName = mbti?.lowercase() + ".png"
        val mbtiImageId = mbtiImageName.let { resources.getIdentifier(it, "drawable", packageName) }
        // TODO: getIdentifier 말고 다른 방법으로 파일 찾아보기
        if (mbtiImageId != 0) {
            binding.imageMbti.setImageResource(mbtiImageId)

        }

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

        val mbtiDescriptionResId = mbti?.let { mbtiDescriptionMap[it] }


        val mbtiDescription = mbtiDescriptionResId?.let { getString(it) } ?: getString(R.string.default_description)

        binding.textMbtiDes.text = mbtiDescription

    }
}
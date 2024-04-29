package com.sopt.now.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMypageBinding

class MyPageFragment(userId: String, userPassword: String, userNickname: String, userMbti: String) :
    Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) {
            "바인딩 객체 생성 완료"
        }

    private val id = userId
    private val password = userPassword
    private val nickname = userNickname
    private val mbti = userMbti


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textNickname.text = getString(R.string.nickname, nickname)
        binding.textMbti.text = getString(R.string.mbti, mbti)
        binding.textId.text = id
        binding.textPw.text = password


//        val mbtiImageName = mbti.lowercase() + ".png"
//        val mbtiImageId = mbtiImageName.let { resources.getIdentifier(it, "drawable",null) }
//        if (mbtiImageId != 0) {
//            binding.imageMbti.setImageResource(mbtiImageId)
//
//        }

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

        val mbtiDescriptionResId = mbti.let { mbtiDescriptionMap[it] }


        val mbtiDescription =
            mbtiDescriptionResId?.let { getString(it) } ?: getString(R.string.default_description)

        binding.textMbtiDes.text = mbtiDescription


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
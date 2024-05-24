package com.sopt.now.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopt.now.activity.ChangePasswordActivity
import com.sopt.now.compose.R
import com.sopt.now.compose.databinding.FragmentMypageBinding

class MyPageFragment(userId: String, userNickname: String, userPhone: String, memberId: Int) :
    Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) {
            "바인딩 객체 생성 완료"
        }

    private val id = userId
    private val nickname = userNickname
    private val phone = userPhone

    private val memberID = memberId


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
        binding.textPhone.text = phone
        binding.textId.text = id
        binding.changePw.setOnClickListener {
            Log.d("MyPapgeFragment", "go ChangePasswordActivity")
            val intentChangePassword = Intent(activity, ChangePasswordActivity::class.java).apply {
                putExtra("memberId", memberID)
            }
            startActivity(intentChangePassword)
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
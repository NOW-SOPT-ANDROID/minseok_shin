package com.sopt.now.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sopt.now.dataClass.RequestSignUpDto
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.viewModel.SignUpViewModel


class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        binding.signupBtn.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val signUpRequest = getSignUpRequestDto()
        viewModel.signUp(signUpRequest)
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.signUpState.collect { state ->
                when (state) {
                    is SignUpViewModel.SignUpState.Idle -> {
                        // No action needed
                    }

                    is SignUpViewModel.SignUpState.Loading -> {
                        // Show loading indicator if needed
                    }

                    is SignUpViewModel.SignUpState.Success -> {
                        Toast.makeText(
                            this@SignUpActivity,
                            "회원가입 성공 유저의 ID는 ${state.userId} 입니다",
                            Toast.LENGTH_SHORT,
                        ).show()
                        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }

                    is SignUpViewModel.SignUpState.Error -> {
                        Toast.makeText(
                            this@SignUpActivity,
                            "회원가입 실패: ${state.message}",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
            }
        }
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        val id = binding.editTextId.text.toString()
        val password = binding.editTextPw.text.toString()
        val nickname = binding.editTextNickname.text.toString()
        val phoneNumber = binding.editTextPhone.text.toString()
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phoneNumber
        )
    }
}

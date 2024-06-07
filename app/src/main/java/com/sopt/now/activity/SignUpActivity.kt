package com.sopt.now.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.viewModel.SignUpViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        collectSignUpState()
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

    private fun collectSignUpState() {
        viewModel.signupState.flowWithLifecycle(lifecycle).onEach { signupState ->
            when (signupState) {
                is SignUpViewModel.SignupState.Success -> {
                    val intentLogin =
                        Intent(this@SignUpActivity, LoginActivity::class.java)
                    startActivity(intentLogin)
                }

                else -> Unit
            }
        }.launchIn(lifecycleScope)
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

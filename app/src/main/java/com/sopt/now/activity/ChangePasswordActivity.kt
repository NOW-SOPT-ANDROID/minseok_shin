package com.sopt.now.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sopt.now.data.model.RequestPasswordDto
import com.sopt.now.databinding.ActivityChangePasswordBinding
import com.sopt.now.viewModel.ChangePasswordViewModel

class ChangePasswordActivity : AppCompatActivity() {

    private val binding by lazy { ActivityChangePasswordBinding.inflate(layoutInflater) }
    private val viewModel: ChangePasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("ChangePassword", "view onCreate")
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        val memberId = intent.getIntExtra("memberId", 0)

        binding.changePasswordBtn.setOnClickListener {
            changePassword(memberId)
        }
    }

    private fun getPasswordRequestDto(): RequestPasswordDto {
        val previousPw = binding.editTextPreviousPw.text.toString()
        val newPw = binding.editTextNewPw.text.toString()
        val newPwCheck = binding.editTextPwCheck.text.toString()
        return RequestPasswordDto(
            previousPassword = previousPw,
            newPassword = newPw,
            newPasswordVerification = newPwCheck,
        )
    }

    private fun changePassword(memberId: Int) {
        val passwordRequest = getPasswordRequestDto()
        viewModel.changePassword(
            memberId,
            passwordRequest.previousPassword,
            passwordRequest.newPassword,
            passwordRequest.newPasswordVerification
        )
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.changePassWordState.collect { state ->
                when (state) {
                    is ChangePasswordViewModel.ChangePasswordState.Success -> {
                        Toast.makeText(
                            this@ChangePasswordActivity,
                            "비밀번호 변경 완료! 새 비밀번호로 로그인 하세요",
                            Toast.LENGTH_SHORT,
                        ).show()

                        val intent = Intent(this@ChangePasswordActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    is ChangePasswordViewModel.ChangePasswordState.Error -> {
                        Toast.makeText(
                            this@ChangePasswordActivity,
                            "비밀번호 변경 실패: ${state.message}",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }

                    ChangePasswordViewModel.ChangePasswordState.Idle -> Unit
                    ChangePasswordViewModel.ChangePasswordState.Loading -> Unit
                }
            }
        }
    }
}

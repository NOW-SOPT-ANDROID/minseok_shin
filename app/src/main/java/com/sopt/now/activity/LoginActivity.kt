package com.sopt.now.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sopt.now.dataClass.RequestLogInDto
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        binding.loginBtn.setOnClickListener {
            login()
        }
        binding.goSignupBtn.setOnClickListener {
            val intentSignup = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intentSignup)
        }
    }

    private fun login() {
        val id = binding.editTextId.text.toString()
        val password = binding.editTextPw.text.toString()
        val loginRequest = RequestLogInDto(id, password)
        viewModel.login(loginRequest)
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginState.collect { state ->
                when (state) {
                    is LoginViewModel.LoginState.Idle -> {
                        // No action needed
                    }

                    is LoginViewModel.LoginState.Loading -> {
                        // Show loading indicator if needed
                    }

                    is LoginViewModel.LoginState.Success -> {
                        Toast.makeText(
                            this@LoginActivity,
                            "로그인 성공 memberID: ${state.memberId}",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intentHome =
                            Intent(this@LoginActivity, HomeActivity::class.java).apply {
                                putExtra("memberId", state.memberId)
                                flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                        startActivity(intentHome)
                        Log.d("LoginActivity", "put ${state.memberId} to HomeActivity")
                    }

                    is LoginViewModel.LoginState.Error -> {
                        Toast.makeText(
                            this@LoginActivity,
                            "로그인 실패: ${state.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}

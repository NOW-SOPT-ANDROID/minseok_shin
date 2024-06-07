package com.sopt.now.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.data.model.RequestLogInDto
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.viewModel.LoginViewModel
import com.sopt.now.viewModel.NavViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        collectLoginState()
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

    private fun collectLoginState() {
        val navViewModel: NavViewModel by viewModels()
        viewModel.loginState.flowWithLifecycle(lifecycle).onEach { loginState ->
            when (loginState) {
                is LoginViewModel.LoginState.Success<*> -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "로그인 성공 memberID: ${loginState.data}",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intentHome =
                        Intent(this@LoginActivity, HomeActivity::class.java).apply {
                            flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                    navViewModel.memberId = loginState.data as Int
                    startActivity(intentHome)
                    Log.d("LoginActivity", "put ${loginState.data} to HomeActivity")
                }

                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }


}

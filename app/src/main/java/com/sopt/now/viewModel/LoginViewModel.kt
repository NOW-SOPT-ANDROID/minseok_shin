package com.sopt.now.viewModel

import androidx.lifecycle.ViewModel
import com.sopt.now.ServicePool
import com.sopt.now.dataClass.RequestLogInDto
import com.sopt.now.dataClass.ResponseDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val authService = ServicePool.authService

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(loginRequest: RequestLogInDto) {
        _loginState.value = LoginState.Loading
        authService.login(loginRequest).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(call: Call<ResponseDto>, response: Response<ResponseDto>) {
                if (response.isSuccessful) {
                    val memberId = response.headers()["Location"]?.toIntOrNull()
                    if (memberId != null) {
                        _loginState.value = LoginState.Success(memberId)
                    } else {
                        _loginState.value = LoginState.Error("Invalid memberId")
                    }
                } else {
                    _loginState.value = LoginState.Error("Login failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                _loginState.value = LoginState.Error(t.message ?: "Unknown error")
            }
        })
    }

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        data class Success(val memberId: Int) : LoginState()
        data class Error(val message: String) : LoginState()
    }
}

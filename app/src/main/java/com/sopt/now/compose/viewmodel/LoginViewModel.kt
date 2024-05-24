package com.sopt.now.compose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.ServicePool
import com.sopt.now.compose.data.RequestLogInDto
import com.sopt.now.compose.data.ResponseDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _loginState =
        MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(loginRequest: RequestLogInDto, onResult: (Boolean, Int) -> Unit) {
        _loginState.value = LoginState.Loading

        authService.logIn(loginRequest).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(call: Call<ResponseDto>, response: Response<ResponseDto>) {
                if (response.isSuccessful) {
                    val memberId = response.headers()["Location"]!!.toInt()
                    _loginState.value = LoginState.Success
                    onResult(true, memberId)
                } else {
                    _loginState.value = LoginState.Error("로그인 실패")
                    onResult(false, -1)
                }
            }

            override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                _loginState.value = LoginState.Error("로그인 요청 실패: ${t.message}")
                Log.d("LoginViewModel", "로그인 요청 실패: ${t.message}")
                onResult(false, -1)
            }
        })
    }

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        object Success : LoginState()
        data class Error(val message: String) : LoginState()
    }
}
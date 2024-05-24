package com.sopt.now.compose.viewmodel

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.ServicePool
import com.sopt.now.compose.data.RequestSignUpDto
import com.sopt.now.compose.data.ResponseDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _signupState =
        MutableStateFlow<SignupState>(SignupState.Idle)
    val signupState: StateFlow<SignupState> = _signupState

    fun signup(signUpRequest: RequestSignUpDto) {
        _signupState.value = SignupState.Loading

        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(call: Call<ResponseDto>, response: Response<ResponseDto>) {
                if (response.isSuccessful) {
                    _signupState.value = SignupState.Success
                } else {
                    _signupState.value = SignupState.Error("회원가입 실패")
                }
            }

            override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                _signupState.value = SignupState.Error("서버 에러 발생")
            }
        })
    }

    sealed class SignupState {
        object Idle : SignupState()
        object Loading : SignupState()
        object Success : SignupState()
        data class Error(val message: String) : SignupState()
    }
}

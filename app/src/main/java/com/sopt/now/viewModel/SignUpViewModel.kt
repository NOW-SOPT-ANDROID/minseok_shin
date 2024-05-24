package com.sopt.now.viewModel

import androidx.lifecycle.ViewModel
import com.sopt.now.ServicePool
import com.sopt.now.dataClass.RequestSignUpDto
import com.sopt.now.dataClass.ResponseDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val authService = ServicePool.authService

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState

    fun signUp(signUpRequest: RequestSignUpDto) {
        _signUpState.value = SignUpState.Loading
        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseDto> {
            override fun onResponse(call: Call<ResponseDto>, response: Response<ResponseDto>) {
                if (response.isSuccessful) {
                    val userId = response.headers()["Location"]?.toIntOrNull()
                    if (userId != null) {
                        _signUpState.value = SignUpState.Success(userId)
                    } else {
                        _signUpState.value = SignUpState.Error("Invalid userId")
                    }
                } else {
                    _signUpState.value = SignUpState.Error("Sign up failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                _signUpState.value = SignUpState.Error(t.message ?: "Unknown error")
            }
        })
    }

    sealed class SignUpState {
        data object Idle : SignUpState()
        data object Loading : SignUpState()
        data class Success(val userId: Int) : SignUpState()
        data class Error(val message: String) : SignUpState()
    }
}

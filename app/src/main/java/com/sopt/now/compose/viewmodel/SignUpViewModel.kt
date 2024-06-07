package com.sopt.now.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.ServicePool
import com.sopt.now.compose.data.model.RequestSignUpDto
import com.sopt.now.compose.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    private val authRepository: AuthRepository = AuthRepository(ServicePool.authService)

    private val _signupState =
        MutableStateFlow<SignupState>(SignupState.Idle)
    val signupState: StateFlow<SignupState> = _signupState

    suspend fun signup(signUpRequest: RequestSignUpDto) {
        _signupState.value = SignupState.Loading
        viewModelScope.launch {
            runCatching {
                authRepository.signUp(signUpRequest)
            }.onSuccess { _signupState.value = SignupState.Success }
                .onFailure { _signupState.value = SignupState.Error("회원가입 실패") }
        }
    }

    sealed class SignupState {
        object Idle : SignupState()
        object Loading : SignupState()
        object Success : SignupState()
        data class Error(val message: String) : SignupState()
    }
}

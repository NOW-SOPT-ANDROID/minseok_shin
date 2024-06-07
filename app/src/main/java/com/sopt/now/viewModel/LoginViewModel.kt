package com.sopt.now.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.ServicePool
import com.sopt.now.data.model.RequestLogInDto
import com.sopt.now.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val authRepository: AuthRepository = AuthRepository(ServicePool.authService)

    private val _loginState =
        MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState


    fun login(loginRequest: RequestLogInDto) {
        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            runCatching {
                authRepository.logIn(loginRequest)
            }.onSuccess {
                _loginState.value = LoginState.Success(it)
            }.onFailure {
                _loginState.value = LoginState.Error("로그인 변경 실패")
            }
        }

    }

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        data class Success<T>(val data: T) : LoginState()
        data class Error(val message: String) : LoginState()
    }
}

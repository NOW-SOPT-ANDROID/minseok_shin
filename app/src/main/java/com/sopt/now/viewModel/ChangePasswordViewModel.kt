package com.sopt.now.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.ServicePool
import com.sopt.now.data.model.RequestPasswordDto
import com.sopt.now.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ChangePasswordViewModel : ViewModel() {

    private val authRepository: AuthRepository = AuthRepository(ServicePool.authService)

    private val _changePasswordState =
        MutableStateFlow<ChangePasswordState>(ChangePasswordState.Idle)
    val changePassWordState: StateFlow<ChangePasswordState> =
        _changePasswordState

    fun changePassword(
        memberId: Int,
        previousPassword: String,
        newPassword: String,
        newPasswordVerification: String
    ) {
        _changePasswordState.value = ChangePasswordState.Loading
        val passwordRequest =
            RequestPasswordDto(previousPassword, newPassword, newPasswordVerification)

        viewModelScope.launch {
            runCatching {
                authRepository.changePassword(memberId, passwordRequest)
            }.onSuccess {
                _changePasswordState.value = ChangePasswordState.Success
            }.onFailure { _changePasswordState.value = ChangePasswordState.Error("비밀번호 변경 실패") }
        }
    }

    sealed class ChangePasswordState {
        object Idle : ChangePasswordState()
        object Loading : ChangePasswordState()
        object Success : ChangePasswordState()
        data class Error(val message: String) : ChangePasswordState()
    }
}


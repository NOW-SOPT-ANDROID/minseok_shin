package com.sopt.now.viewModel

import androidx.lifecycle.ViewModel
import com.sopt.now.ServicePool
import com.sopt.now.dataClass.RequestPasswordDto
import com.sopt.now.dataClass.ResponseDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordViewModel : ViewModel() {
    private val authService = ServicePool.authService

    private val _changePasswordState =
        MutableStateFlow<ChangePasswordState>(ChangePasswordState.Idle)
    val changePasswordState: StateFlow<ChangePasswordState> = _changePasswordState

    fun changePassword(memberId: Int, passwordRequest: RequestPasswordDto) {
        _changePasswordState.value = ChangePasswordState.Loading
        authService.changePassword(memberId, passwordRequest)
            .enqueue(object : Callback<ResponseDto> {
                override fun onResponse(call: Call<ResponseDto>, response: Response<ResponseDto>) {
                    if (response.isSuccessful) {
                        _changePasswordState.value = ChangePasswordState.Success
                    } else {
                        _changePasswordState.value = ChangePasswordState.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseDto>, t: Throwable) {
                    _changePasswordState.value =
                        ChangePasswordState.Error(t.message ?: "Unknown error")
                }
            })
    }

    sealed class ChangePasswordState {
        object Idle : ChangePasswordState()
        object Loading : ChangePasswordState()
        object Success : ChangePasswordState()
        data class Error(val message: String) : ChangePasswordState()
    }


}



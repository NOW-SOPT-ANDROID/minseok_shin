package com.sopt.now.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.ServicePool
import com.sopt.now.compose.data.model.ResponseInfoDto
import com.sopt.now.compose.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val authRepository: AuthRepository = AuthRepository(ServicePool.authService)

    private val _homeState =
        MutableStateFlow<HomeState>(HomeState.Idle)
    val homeState: StateFlow<HomeState> = _homeState

    fun searchInfo(memberId: Int) {
        _homeState.value = HomeState.Loading
        viewModelScope.launch {
            runCatching {
                authRepository.getInfo(memberId)
            }.onSuccess { responseInfoDto ->
                _homeState.value = HomeState.Success(responseInfoDto = responseInfoDto)
            }.onFailure {
                _homeState.value = HomeState.Error("Error")

            }
        }
    }

    sealed class HomeState {
        object Idle : HomeState()
        object Loading : HomeState()
        data class Success(val responseInfoDto: ResponseInfoDto) : HomeState()
        data class Error(val message: String) : HomeState()
    }
}

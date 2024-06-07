package com.sopt.now.compose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.ServicePool
import com.sopt.now.compose.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private val authRepository: AuthRepository = AuthRepository(ServicePool.authService)

    private val _myPageState =
        MutableStateFlow<MyPageState>(MyPageState.Idle)

    var userId: String by mutableStateOf("")
        private set

    var userNickname: String by mutableStateOf("")
        private set

    var userPhone: String by mutableStateOf("")
        private set

    fun searchInfo(memberId: Int) {

        _myPageState.value = MyPageState.Loading


        viewModelScope.launch {
            runCatching {
                authRepository.getInfo(memberId)
            }.onSuccess {
                _myPageState.value = MyPageState.Success
            }.onFailure {
                _myPageState.value = MyPageState.Error("Error")
            }
        }


//        authService.info(memberId).enqueue(object : Callback<ResponseInfoDto> {
//            override fun onResponse(
//                call: Call<ResponseInfoDto>,
//                response: Response<ResponseInfoDto>
//            ) {
//                if (response.isSuccessful) {
//                    val responseData = response.body()?.data
//                    userId = responseData?.authenticationId ?: ""
//                    userNickname = responseData?.nickname ?: ""
//                    userPhone = responseData?.phone ?: ""
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseInfoDto>, t: Throwable) {
//                Log.d("MyPageError", "error")
//            }
//        })
    }

    sealed class MyPageState {
        object Idle : MyPageState()
        object Loading : MyPageState()
        object Success : MyPageState()
        data class Error(val message: String) : MyPageState()
    }
}

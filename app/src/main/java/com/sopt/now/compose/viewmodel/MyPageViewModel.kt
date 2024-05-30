package com.sopt.now.compose.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.ServicePool
import com.sopt.now.compose.data.ResponseInfoDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    var userId: String by mutableStateOf("")
        private set

    var userNickname: String by mutableStateOf("")
        private set

    var userPhone: String by mutableStateOf("")
        private set

    fun searchInfo(memberId: Int) {
        authService.info(memberId).enqueue(object : Callback<ResponseInfoDto> {
            override fun onResponse(
                call: Call<ResponseInfoDto>,
                response: Response<ResponseInfoDto>
            ) {
                if (response.isSuccessful) {
                    val responseData = response.body()?.data
                    userId = responseData?.authenticationId ?: ""
                    userNickname = responseData?.nickname ?: ""
                    userPhone = responseData?.phone ?: ""
                }
            }

            override fun onFailure(call: Call<ResponseInfoDto>, t: Throwable) {
                Log.d("MyPageError", "error")
            }
        })
    }
}

package com.sopt.now.compose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.ServicePool
import com.sopt.now.compose.data.ResponseInfoDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _myName = MutableStateFlow("")
    val myName: StateFlow<String> = _myName

    fun searchInfo(memberId: Int) {
        authService.info(memberId).enqueue(object : Callback<ResponseInfoDto> {
            override fun onResponse(
                call: Call<ResponseInfoDto>,
                response: Response<ResponseInfoDto>
            ) {
                if (response.isSuccessful) {
                    _myName.value = response.body()?.data?.nickname ?: ""
                } else {
                    Log.d("error", "error")
                }
            }

            override fun onFailure(call: Call<ResponseInfoDto>, t: Throwable) {
                Log.d("error", "error")

            }
        })
    }
}

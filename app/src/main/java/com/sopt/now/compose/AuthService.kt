package com.sopt.now.compose

import com.sopt.now.compose.data.RequestLogInDto
import com.sopt.now.compose.data.RequestSignUpDto
import com.sopt.now.compose.data.ResponseLogInDto
import com.sopt.now.compose.data.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>

    @POST("member/login")
    fun logIn(
        @Body request: RequestLogInDto,
    ): Call<ResponseLogInDto>
}
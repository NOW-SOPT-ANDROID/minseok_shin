package com.sopt.now

import com.sopt.now.dataClass.RequestInfoDto
import com.sopt.now.dataClass.RequestLogInDto
import com.sopt.now.dataClass.RequestSignUpDto
import com.sopt.now.dataClass.ResponseInfoDto
import com.sopt.now.dataClass.ResponseLogInDto
import com.sopt.now.dataClass.ResponseSignUpDto
import retrofit2.Call

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>

    @POST("member/login")
    fun login(@Body request: RequestLogInDto): Call<ResponseLogInDto>

    @POST("member/info")
    fun info(@Body request: RequestInfoDto): Call<ResponseInfoDto>
}

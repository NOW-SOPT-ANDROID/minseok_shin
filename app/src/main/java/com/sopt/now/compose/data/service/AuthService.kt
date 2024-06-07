package com.sopt.now.compose.data.service

import com.sopt.now.compose.data.model.RequestLogInDto
import com.sopt.now.compose.data.model.RequestPasswordDto
import com.sopt.now.compose.data.model.RequestSignUpDto
import com.sopt.now.compose.data.model.ResponseDto
import com.sopt.now.compose.data.model.ResponseInfoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun signUp(
        @Body request: RequestSignUpDto,
    ): ResponseDto

    @POST("member/login")
    suspend fun logIn(
        @Body request: RequestLogInDto,
    ): Response<ResponseDto>

    @GET("member/info")
    suspend fun info(@Header("memberId") memberId: Int): ResponseInfoDto

    @PATCH("member/password")
    suspend fun changePassword(
        @Header("memberId") memberId: Int,
        @Body request: RequestPasswordDto
    ): ResponseDto
}
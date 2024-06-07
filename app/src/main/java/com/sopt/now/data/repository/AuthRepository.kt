package com.sopt.now.data.repository

import com.sopt.now.data.model.RequestLogInDto
import com.sopt.now.data.model.RequestPasswordDto
import com.sopt.now.data.model.RequestSignUpDto
import com.sopt.now.data.model.ResponseDto
import com.sopt.now.data.model.ResponseInfoDto
import com.sopt.now.service.AuthService

class AuthRepository(private val authService: AuthService) {
    suspend fun signUp(request: RequestSignUpDto): ResponseDto = authService.signUp(request)

    suspend fun logIn(request: RequestLogInDto): Int? =
        authService.logIn(request).headers()["Location"]?.toInt()

    suspend fun getInfo(memberId: Int): ResponseInfoDto = authService.info(memberId)

    suspend fun changePassword(memberId: Int, request: RequestPasswordDto): ResponseDto =
        authService.changePassword(memberId, request)

}
package com.sopt.now.compose.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLogInDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("password")
    val password: String,
)
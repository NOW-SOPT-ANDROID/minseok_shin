package com.sopt.now.dataClass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLogInDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("password")
    val password: String,
//    @SerialName("nickname")
//    val nickname: String,
//    @SerialName("phone")
//    val phone: String,
)
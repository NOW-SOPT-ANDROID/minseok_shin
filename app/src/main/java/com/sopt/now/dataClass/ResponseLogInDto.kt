package com.sopt.now.dataClass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLogInDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
)
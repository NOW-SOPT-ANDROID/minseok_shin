package com.sopt.now.compose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    @SerialName("code")
    val status: Int,
    @SerialName("message")
    val message: String,
)
package com.sopt.now.dataClass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestInfoDto(
    @SerialName("memberId")
    val memberId: Int,
)
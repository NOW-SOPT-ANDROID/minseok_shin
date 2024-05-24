package com.sopt.now.dataClass

import androidx.annotation.DrawableRes

data class Friend(
    @DrawableRes val profileImage: Int,
    val name: String,
    val selfDescription: String,
)
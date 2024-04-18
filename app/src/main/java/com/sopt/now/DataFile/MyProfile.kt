package com.sopt.now.DataFile

import androidx.annotation.DrawableRes

data class MyProfile(
    @DrawableRes val profileImage: Int,
    val name: String,
    val selfDescription: String,
)
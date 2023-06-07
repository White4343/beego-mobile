package com.white.beego.models

data class UserResponse(
    val token: String,
    val userData: UserData
)
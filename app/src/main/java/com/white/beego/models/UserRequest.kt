package com.white.beego.models

data class UserRequest(
    val email: String,
    val fullName: String,
    val password: String
)
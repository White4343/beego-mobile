package com.white.beego.models

data class BeehiveRequest(
    val description: String,
    val deviceID: String,
    val name: String,
    val beeCount: Int,
    val honeyCount: Int,
)
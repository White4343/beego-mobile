package com.white.beego.models

data class BeehiveResponse(
    val __v: Int,
    val _id: String,
    val apiary: String,
    val createdAt: String,
    val description: String,
    val beeCount: String,
    val honeyCount: String,
    val deviceID: String,
    val name: String,
    val updatedAt: String
)
package com.white.beego.models

data class QueenRequest(
    val name: String,
    val introducedFrom: String,
    val isOut : Boolean,
    val description: String
)
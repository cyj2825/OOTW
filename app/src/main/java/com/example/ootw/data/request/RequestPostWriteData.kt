package com.example.ootw.data.request

data class RequestPostWriteData(
    val title: String,
    val body: String,
    val temp: Int,
    val item: String,
    val imgURL: String,
)
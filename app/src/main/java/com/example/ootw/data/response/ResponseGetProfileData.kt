package com.example.ootw.data.response

import com.example.ootw.model.Post
import com.example.ootw.model.User

data class ResponseGetProfileData(
    val message: String,
    val userProfile: User,
)
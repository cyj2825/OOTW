package com.example.ootw.data.response

import com.example.ootw.model.Post
import com.example.ootw.model.User
import com.kakao.sdk.user.model.Gender

data class ResponseGetProfileData(
    val message: String,
    val userProfile: UserProfileData,
)

data class UserProfileData (
    val nickname: String,
    val gender: String,
    val birth: String,
    val area: String,
    val area_detail: String,
    val cold_sensitivity: Int,
    val hot_sensitivity: Int
    )
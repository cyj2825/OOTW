package com.example.ootw.data.request

import com.google.gson.annotations.SerializedName

data class RequestSignupData(
    @SerializedName("login_id")
    val loginId: String,
    val password: String,
    val email: String,
    val birth: String,
    val nickname: String,
    val gender: String,
    @SerializedName("cold_sensitivity")
    val coldSensitivity: Int,
    @SerializedName("hot_sensitivity")
    val hotSensitivity: Int,
    val area: String,
    @SerializedName("area_detail")
    val areaDetail: String,
    @SerializedName("profile_img")
    val profileImg: String,     // img 파일이 아닌 img 주소
)

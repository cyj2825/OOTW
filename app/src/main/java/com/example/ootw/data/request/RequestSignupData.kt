package com.example.ootw.data.request

data class RequestSignupData(
    val login_id: String,
    val password: String,
    val email: String,
    val birth: String,
    val nickname: String,
    val gender: String,
    val cold_sensitivity: Int,
    val hot_sensitivity: Int,
    val area: String,
    val area_detail: String,
    val profile_img: String,     // img 파일이 아닌 img 주소
)

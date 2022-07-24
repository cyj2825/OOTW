package com.example.ootw.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login_id") val login_id: String,

    @SerializedName("password") val password: String,

    @SerializedName("email") val email: String,

    @SerializedName("birth") val birth: String, // DB 변수타입은 DATEONLY

    @SerializedName("nickname") val nickname: String,

    @SerializedName("gender") val gender: String,

    @SerializedName("cold_sensitivity") val cold_sensitivity: Int,

    @SerializedName("hot_sensitivity") val hot_sensitivity: Int,

    @SerializedName("area") val area: String,

    @SerializedName("area_detail") val area_detail: String,

    @SerializedName("profile_img") val profile_img: String
)

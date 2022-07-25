package com.example.ootw.api

import com.example.ootw.data.response.ResponseGetProfileData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetProfileService {
    @GET("/profile")
    fun getProfile(@Query("login_id") logindId: String)
    : Call<ResponseGetProfileData>
}
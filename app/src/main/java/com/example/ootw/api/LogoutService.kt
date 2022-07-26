package com.example.ootw.api

import com.example.ootw.data.response.ResponseLogoutData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LogoutService {
    @GET("/auth/logout")
    fun postLogout():Call<ResponseLogoutData>
}
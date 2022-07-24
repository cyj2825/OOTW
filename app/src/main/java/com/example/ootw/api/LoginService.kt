package com.example.ootw.api

import com.example.ootw.data.request.RequestLoginData
import com.example.ootw.data.response.ResponseLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/auth/login")
    fun postLogin(
        @Body body: RequestLoginData
    ) : Call<ResponseLoginData>
}
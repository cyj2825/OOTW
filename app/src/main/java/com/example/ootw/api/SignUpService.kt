package com.example.ootw.api

import com.example.ootw.data.request.RequestSignupData
import com.example.ootw.data.response.ResponseSignupData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("/auth/join")
    fun postSignup(
        @Body body: RequestSignupData
    ) : Call<ResponseSignupData>
}
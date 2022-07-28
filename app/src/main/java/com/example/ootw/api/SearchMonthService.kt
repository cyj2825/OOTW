package com.example.ootw.api

import com.example.ootw.data.response.ResponseSearchMonthData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchMonthService {
    @GET("/post/search")
    fun postSearchMonth(@Query("month") month: Int): Call<ResponseSearchMonthData>
}
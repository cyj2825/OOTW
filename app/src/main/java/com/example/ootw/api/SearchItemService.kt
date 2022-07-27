package com.example.ootw.api

import com.example.ootw.data.response.ResponseSearchItemData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchItemService {
    @GET("/post/search")
    fun postSearchItem(@Query("item") item: String): Call<ResponseSearchItemData>
}
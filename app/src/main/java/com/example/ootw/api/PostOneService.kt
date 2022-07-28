package com.example.ootw.api

import com.example.ootw.data.response.ResponsePostOneData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostOneService {
    @GET("/post/read/{postid}")
    fun getonePosts(@Path("postid") userId: Int): Call<ResponsePostOneData>
}
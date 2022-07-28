package com.example.ootw.api

import com.example.ootw.data.response.ResponsePostLikeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostLikeService {
    @GET("/post/like")
    fun getlikePosts(@Query("userId") userId: Int): Call<ResponsePostLikeData>
}
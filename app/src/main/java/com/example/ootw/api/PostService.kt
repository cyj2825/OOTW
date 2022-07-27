package com.example.ootw.api

import com.example.ootw.data.response.ResponsePostData
import retrofit2.Call
import retrofit2.http.GET

interface PostService {
    @GET("/post")
    suspend fun getAllPosts(): Call<ResponsePostData>
}
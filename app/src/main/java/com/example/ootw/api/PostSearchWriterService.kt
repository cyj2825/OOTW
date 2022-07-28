package com.example.ootw.api

import com.example.ootw.data.response.ResponsePostSearchWriterData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostSearchWriterService {
    @GET("/post/search/writer")
    fun getwriterPosts(@Query("userId") userId: Int): Call<ResponsePostSearchWriterData>
}
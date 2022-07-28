package com.example.ootw.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostSearchWriterServiceCreator {
    private const val BASE_URL = "http://ec2-15-164-49-16.ap-northeast-2.compute.amazonaws.com:80/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postsearchwriterService: PostSearchWriterService = retrofit.create(PostSearchWriterService::class.java)
}
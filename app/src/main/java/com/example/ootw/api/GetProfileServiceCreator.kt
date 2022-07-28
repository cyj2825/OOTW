package com.example.ootw.api

import com.example.ootw.constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GetProfileServiceCreator {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getProfileService: GetProfileService = retrofit.create(GetProfileService::class.java)

}
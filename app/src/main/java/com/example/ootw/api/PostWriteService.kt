package com.example.ootw.api

import com.example.ootw.data.request.RequestPostWriteData
import com.example.ootw.data.response.ResponsePostWriteData
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*

interface PostWriteService {
    @POST("/post/{userId}")
    fun postPostWrite(
        @Query("userId") userId: Int,
        @Body body: RequestPostWriteData
    ) : Call<ResponsePostWriteData>
}
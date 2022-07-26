package com.example.ootw.api

import com.example.ootw.data.request.RequestPostWriteData
import com.example.ootw.data.response.ResponsePostWriteData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostWriteService {
    @POST("/post")
    fun postPostWrite(
        @Body body: RequestPostWriteData
    ) : Call<ResponsePostWriteData>
}
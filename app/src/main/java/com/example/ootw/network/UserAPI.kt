package com.example.ootw.network

import com.example.ootw.model.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {
    @FormUrlEncoded
    @POST("UserSelect.php")
    fun getUser(
        @Field("email") email: String
    ): Call<User>
}
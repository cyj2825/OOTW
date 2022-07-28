package com.example.ootw.data.response

import com.google.gson.annotations.SerializedName

data class ResponseSearchMonthData(
    val posts: List<Post4>
)
data class Post4(
    val id: Int,
    val title: String,
    val body: String,
    val temp: Int,
    val item: String,
    @SerializedName("imgURL")
    val imgURL: String,
    val createdAt: String,
    val updatedAt: String,
    val writer: Int,
    @SerializedName("item_Id")
    val itemId: Int

)

package com.example.ootw.data.response

import com.google.gson.annotations.SerializedName

data class ResponseSearchItemData(
    val posts: List<Posts>
)
data class Posts(
    val id: Int,
    val title: String,
    val body: String,
    val temp: Int,
    val item: String,
    val createdAt: String,
    val updatedAt: String,
    val writer: Int,
    @SerializedName("item_Id")
    val itemId: Int
)

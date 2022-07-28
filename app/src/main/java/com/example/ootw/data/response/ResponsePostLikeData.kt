package com.example.ootw.data.response

import com.google.gson.annotations.SerializedName

data class ResponsePostLikeData(
    val posts: List<Post1>
)
data class Post1(
    @SerializedName("postId")
    val postId: Int,   // 좋아요 누른 게시글 id
    @SerializedName("userId")
    val userId: Int,   // userId
    @SerializedName("Post")
    val Post: Post2?
){
    data class Post2(
        val id: Int,
        val title: String,
        val body: String,
        val temp: Int,
        val item: String,
        // @SerializedName("imgURL")
        // val imgURL: String,
        val createdAt: String,
        val updatedAt: String,
        val writer: Int,
        @SerializedName("item_Id")
        val itemId: Int
    )
}



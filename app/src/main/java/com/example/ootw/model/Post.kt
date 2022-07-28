package com.example.ootw.model

class Post(var id: String? = null, // post id
           var title: String? = null,
           var body: String? = null,
           var imgURL: String? = null,
           var temp: Int? = null,
           var item: String? = null,
           var createdAt: String? = null,
           var updatedeAt: String? = null,
           var writer: String? = null,
           var itemId: String? = null,
)
//  하늘상태, 옷 카테고리, 지역 필드는 없어도 되는지?
//  title 필드 의미?
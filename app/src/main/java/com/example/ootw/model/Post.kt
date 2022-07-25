package com.example.ootw.model

class Post(var uid: String? = null,
           var userId: String? = null,
           var imageUrl: String? = null,
           var weather: String? = null,
           var icon: String? = null,
           var region: String? = null,
           var category: String? = null,
           var clothes: String? = null,
           var explain: String? = null,
           var timestamp: String? = null,
           var favorites: MutableMap<String, Boolean> = HashMap()
)

//class Post(var id: String? = null,
//           var title: String? = null,
//           var body: String? = null,
//           var temp: String? = null,
//           var item: String? = null,
//           var createdAt: String? = null,
//           var updatedeAt: String? = null,
//           var writer: String? = null,
//           var itemId: String? = null,
//           // 하늘 상태는 필요 없는지?
//)
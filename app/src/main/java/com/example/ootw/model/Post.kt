package com.example.ootw.model

data class Post(var uid: String? = null,
           var userId: String? = null,
           var imageUrl: String? = null,
           var weather: String? = null,
           var icon: String? = null,
           var region: String? = null,
           var category: String? = null,
           var clothes: String? = null,
           var explain: String? = null,
           var timestamp: String? = null,
           var favorites: MutableMap<String, Boolean> = HashMap()) {

}
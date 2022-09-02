package com.example.retrofitkotlin.view.Model

import com.google.gson.annotations.SerializedName

data class cryptoModel (
    //@SerializedName("currency")
    // javada gereklidir lakin kotlinde olmasada olur
    val currency: String,

    //@SerializedName("price")
    // javada gereklidir lakin kotlinde olmasada olur
    val price: String
)
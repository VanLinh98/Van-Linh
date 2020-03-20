package com.example.baocaotuan3.Model


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("date")
    val date: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("from")
    val from: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("subject")
    val subject: String
)
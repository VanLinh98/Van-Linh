package com.example.baocaotuan3.Model


import com.google.gson.annotations.SerializedName

data class Mail(
    @SerializedName("messages")
    val messages: List<Message>
)
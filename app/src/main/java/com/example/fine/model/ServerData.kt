package com.example.fine.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName


// 서버 response 기본포맷
data class ServerData (
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("data")
    var data: String? = null,
    @SerializedName("message")
    var message: String? = ""
)


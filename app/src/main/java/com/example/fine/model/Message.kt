package com.example.fine.model

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class ServerData_messages (
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("data")
    var data: List<JSONObject>? = null,
    @SerializedName("message")
    var message: String? = ""
)
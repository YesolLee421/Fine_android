package com.example.fine.model

import com.google.gson.annotations.SerializedName

class ServerData_mypage (
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("data")
    var data: MyPageData? = null,
    @SerializedName("message")
    var message: String? = ""
)

class MyPageData (
    @SerializedName("user")
    var user: userData? = null,
    @SerializedName("counselor")
    var counselor: CounselorData? = null
)

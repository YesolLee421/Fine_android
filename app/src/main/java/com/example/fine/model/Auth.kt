package com.example.fine.model

import com.google.gson.annotations.SerializedName

// 회원가입 시 보낼 정보
class Register(var email: String, var nickname: String, var password: String, var type:Int)

// 로그인 시 보낼 정보
class Login(var email: String,  var password: String)

class ServerData_user (
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("data")
    var data: userData? = null,
    @SerializedName("message")
    var message: String? = ""
)

class userData(
    @SerializedName("user_uid")
    var user_uid: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("nickname")
    var nickname: String = "",
    @SerializedName("type")
    var type: Int = 2
)


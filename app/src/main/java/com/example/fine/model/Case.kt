package com.example.fine.model

import com.google.gson.annotations.SerializedName
import java.util.*

class ServerData_cases(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("data")
    var data: List<Case>?,
    @SerializedName("message")
    var message: String?
)

data class ServerData_case (
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("data")
    var data: Case? = null,
    @SerializedName("message")
    var message: String? = ""
)

class createCase (
    var counselor_id: String,
    var totalCase: Int,
    var price: Int,
    var discountRate: Int
)

class Case (
    @SerializedName("id")
    var case_id: Int,
    @SerializedName("counselor_id")
    var counselor_id: String,
    @SerializedName("hasPaper")
    var hasPaper: Boolean,
    @SerializedName("status")
    var status: Int,
    @SerializedName("totalCase")
    var totalCase: Int,
    @SerializedName("usedCase")
    var usedCase: Int,
    @SerializedName("discountRate")
//    var discountRate: Int,
//    @SerializedName("totalPrice")
    var totalPrice: Int,
    @SerializedName("nextCase")
    var nextCase: Date,
    @SerializedName("expireDate")
    var expireDate: Date,
    @SerializedName("fk_user_uid")
    var fk_user_uid: String
)
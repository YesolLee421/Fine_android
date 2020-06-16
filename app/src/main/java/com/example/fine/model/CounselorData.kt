package com.example.fine.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ServerData_counselors (
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("data")
    var data: List<CounselorData>? = null,
    @SerializedName("message")
    var message: String? = ""
)

data class ServerData_counselor (
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("data")
    var data: CounselorData? = null,
    @SerializedName("message")
    var message: String? = ""
)


class CounselorData (
    @SerializedName("user_uid")
    var user_uid: String,
    @SerializedName("name_formal")
    var name_formal: String?,
    @SerializedName("gender")
    var gender: Int?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("picture")
    var picture: String?,
    @SerializedName("isVerified")
    var isVerified: Boolean,
    @SerializedName("intro_1")
    var intro_1: String?,
    @SerializedName("intro_2")
    var intro_2: String?,
    @SerializedName("intro_3")
    var intro_3: String?,
    @SerializedName("certificate")
    var certificate: String?,
    @SerializedName("career")
    var career: String?,
    @SerializedName("education")
    var education: String?,
    @SerializedName("time_prefered")
    var time_prefered: String?,
    @SerializedName("price")
    var price: Int,
    @SerializedName("discount_4w")
    var discount_4w: Int,
    @SerializedName("discount_10w")
    var discount_10w: Int,
    @SerializedName("bank_account")
    var bank_account: String?,
    @SerializedName("count")
    var count: Int,
    @SerializedName("createdAt")
    var createdAt: Date,
    @SerializedName("updatedAt")
    var updatedAt: Date?,
    @SerializedName("deletedAt")
    var deletedAt: Date?
)
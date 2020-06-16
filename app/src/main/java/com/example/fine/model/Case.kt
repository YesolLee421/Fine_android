package com.example.fine.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Case (
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
    var discountRate: Int,
    @SerializedName("totalPrice")
    var totalPrice: Int,
    @SerializedName("nextCase")
    var nextCase: Date,
    @SerializedName("expireDate")
    var expireDate: Date,
    @SerializedName("fk_user_uid")
    var fk_user_uid: String

)
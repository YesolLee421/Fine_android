package com.example.fine.model

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName
import org.json.JSONArray

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

class ChangeProfile(
    var name_formal: String,
    var description: String?,
    var gender: Int
)

class ChangeNickName(var nickname: String)

// 비밀번호 변경 시 보낼 정보
class ChangePassword(
    var pre_password: String,
    var new_password: String,
    var new_password_2: String
)

// 상담사 소개 변경 시 보낼 정보
class ChangeIntro(
    var intro_1: String?,
    var intro_2: String?,
    var intro_3: String?
)

// 상담사 약력 변경 시 보낼 정보
class ChangeExperience(
    var certificate: String?,
    var career: String?,
    var education: String?
)

// 상담사 선호시간 변경 시 보낼 정보
class ChangeTimePrefered(
    var day: JSONArray?,
    var time: JSONArray?
)

// 상담 가격 변경 시 보낼 정보
class ChangePrice(
    var price: Int,
    var discount_4w: Int,
    var discount_10w: Int
)

// 상담사 입금계좌 변경 시 보낼 정보
class ChangeBankAccount(
    var bank_name: String?,
    var account_number: String?
)



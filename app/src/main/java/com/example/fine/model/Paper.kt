package com.example.fine.model

import com.google.gson.annotations.SerializedName
import org.json.JSONArray

// 상담사 선호시간 변경 시 보낼 정보
class ChangePaperAll(
    var gender: Int,
    var birth_year: Int,
    var job: Int?,
    var counselBefore: Int?,
    var clinicBefore: Int?,
    var problem: JSONArray?,
    var symptom: JSONArray?,
    var religion: Int?,
    var education: Int?,
    var livingCondition: Int?,
    var isMarried: Int?,
    var hasMate: Int?,
    var family: String?,
    var request: String?
    )

data class ServerData_paper (
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("data")
    var data: Paper? = null,
    @SerializedName("message")
    var message: String? = ""
)

class Paper (
    @SerializedName("id")
    var id: Int,
    @SerializedName("gender")
    var gender: Int?,
    @SerializedName("birth_year")
    var birth_year: Int?,
    @SerializedName("job")
    var job: Int?,
    @SerializedName("counselBefore")
    var counselBefore: Int?,
    @SerializedName("clinicBefore")
    var clinicBefore: Int?,
    @SerializedName("problem")
    var problem: String?,
    @SerializedName("symptom")
    var symptom: String?,
    @SerializedName("religion")
    var religion: Int?,
    @SerializedName("education")
    var education: Int?,
    @SerializedName("livingCondition")
    var livingCondition: Int?,
    @SerializedName("isMarried")
    var isMarried: Int?,
    @SerializedName("hasMate")
    var hasMate: Int?,
    @SerializedName("family")
    var family: String?,
    @SerializedName("request")
    var request: String?,
    @SerializedName("isCompleted")
    var isCompleted: Boolean,
    @SerializedName("fk_user_uid")
    var fk_user_uid: String

)

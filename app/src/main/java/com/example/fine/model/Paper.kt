package com.example.fine.model

import com.google.gson.annotations.SerializedName

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
    var isMarried: Boolean?,
    @SerializedName("hasMate")
    var hasMate: Boolean?,
    @SerializedName("family")
    var family: String?,
    @SerializedName("request")
    var request: String?,
    @SerializedName("isCompleted")
    var isCompleted: Boolean,
    @SerializedName("fk_user_uid")
    var fk_user_uid: String

)

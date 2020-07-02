package com.example.fine.network

import com.example.fine.model.*
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ServiceAPI {

    // 회원가입
    @POST("/auth/register")
    fun register(@Body register: Register): Call<JsonObject>

    // 로그인
    @POST("/auth/login")
    fun login(@Body login: Login): Call<JsonObject>

    // 유저(상담사포함) 본인 정보 가져오기
    @GET("/mypage")
    fun getUser(@Path("user_uid") user_uid: String): Call<JsonObject>

    // 상담사 정보 가져오기
    @GET("/counselors/{user_uid}")
    fun getCounselor(@Path("user_uid") user_uid: String): Call<ServerData_counselor>

    // 상담사 목록 불러오기
    @GET("/counselors/{sort}/{filter}")
    fun getCounselorsList(@Path("sort") sort: Int, @Path("filter") filter: Int): Call<ServerData_counselors>

    // 마이페이지 정보 불러오기
    @GET("/mypage")
    fun getMyPage() : Call<ServerData_mypage>

    // 마이페이지 공통기능
    // 비밀번호 변경
    @PATCH("/mypage/password")
    fun changePassword(@Body changePassword: ChangePassword) :Call<ServerData_mypage>

    // 마이페이지 일반인 전용기능
    // 닉네임 변경
    @PATCH("/mypage/nickname")
    fun changeNickName(@Body changeNickName: ChangeNickName) :Call<ServerData_mypage>

    // 마이페이지 상담사 전용기능
    // 프로필 변경
    @Multipart
    @PATCH("/mypage/counselor/profile")
    fun changeProfile(
        @Part("name_formal") name_formal: RequestBody,
        @Part("description") description: RequestBody,
        @Part("gender") gender: Int,
        @Part picture: MultipartBody.Part?
    ) : Call<ServerData_mypage>

    // 상담사 소개 변경
    @PATCH("/mypage/counselor/intro")
    fun changeIntro(@Body changeIntro: ChangeIntro) :Call<ServerData_mypage>

    // 상담사 약력 변경
    @PATCH("/mypage/counselor/experience")
    fun changeExperience(@Body changeExperience: ChangeExperience) :Call<ServerData_mypage>

    // 상담사 선호일정 변경
    @PATCH("/mypage/counselor/time_prefered")
    fun changeTimePrefered(@Body changeTimePrefered: ChangeTimePrefered) :Call<ServerData_mypage>

    // 가격 변경
    @PATCH("/mypage/counselor/price")
    fun changePrice(@Body changePrice: ChangePrice) :Call<ServerData_mypage>

    // 입금계좌 변경
    @PATCH("/mypage/counselor/bank_account")
    fun changeBankAccount(@Body changeBankAccount: ChangeBankAccount) :Call<ServerData_mypage>

    // 상담 Case 관련
    // 상담 목록 불러오기
    @GET("/cases")
    fun getCases() : Call<ServerData_cases>

    // 개별 상담 케이스 불러오기
    @GET("/cases/{case_id}")
    fun getCase(@Path("case_id") case_id: Int): Call<ServerData_case>

    // 상담케이스 생성
    @POST("/cases")
    fun createCase(@Body createCase: createCase): Call<ServerData_case>

    // 상담 케이스 변경: 상담 접수지 제출
    @PATCH("/cases/paper/{case_id}")
    fun setPaper(@Path("case_id") case_id: Int): Call<ServerData_case>

    // 상담 접수지 Paper 관련
    @GET("/paper") // 유저가 본인의 접수지 확인
    fun getUserPaper(): Call<ServerData_paper>

    @GET("/paper/{paper_id}") // 상담사가 접수지 확인
    fun getCasePaper(@Path("paper_id") paper_id: Int) : Call<ServerData_paper>


}

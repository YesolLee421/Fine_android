package com.example.fine.network

import com.example.fine.model.*
import com.google.gson.JsonObject
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
    fun changeProfile() : Call<ServerData_mypage>

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

}

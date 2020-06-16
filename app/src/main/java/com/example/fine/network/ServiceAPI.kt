package com.example.fine.network

import com.example.fine.model.*
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

}

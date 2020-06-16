package com.example.fine.network

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AddCookiesInterceptor: Interceptor{
    // sharedPreference에 있는 쿠키를 header.cookie에 넣어주기

    internal lateinit var preferences: SharedPreferences
    internal lateinit var context: Context
    val TAG = "AddCookies"

    constructor(context: Context) {
        this.context = context
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        preferences = context.getSharedPreferences("USERSIGN",0)
        Log.d(TAG, preferences.getString("Cookie", "")!!)

        // req.header에 쿠키 정보 넣기
        builder.addHeader("Cookie", "connect.sid=" + preferences.getString("Cookie", "")!!)
        builder.addHeader("Content-Type", "application/json")

        return chain.proceed(builder.build())
    }
}
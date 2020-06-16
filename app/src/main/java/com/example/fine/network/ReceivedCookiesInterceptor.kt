package com.example.fine.network

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ReceivedCookiesInterceptor: Interceptor {
    // 받은 쿠키를 sharedPreference에 저장

    internal lateinit var preferences: SharedPreferences
    internal lateinit var context: Context
    val TAG = "ReceivedCookies"

    constructor(context: Context){
        this.context = context
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        if (!originalResponse.headers("Set-Cookie").isEmpty()){ //cookie있으면 가져오기
            val cookies = HashSet<String>()
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies.add(header)
            }
            val arr1 = cookies.toString().split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            // name=value 값 추출
            val arr2 = arr1[0].split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            preferences = context.getSharedPreferences("USERSIGN", 0)
            val editor = preferences.edit()
            editor.putString("Cookie", arr2[1])
            editor.apply()
        }
        return originalResponse
    }
}
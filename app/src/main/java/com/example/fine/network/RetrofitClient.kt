package com.example.fine.network

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // okhttp 클라이언트 반환
    fun getClient(context: Context, type: String): OkHttpClient{
        if (type.equals("addCookie")) {
            val addCookiesInterceptor = AddCookiesInterceptor(context)
            val client = OkHttpClient.Builder()
                .addInterceptor(addCookiesInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
            return client
        }
        //쿠키를 받아서 저장할때 쓰는타입
        else if (type.equals("receiveCookie")) {
            val receivedCookiesInterceptor = ReceivedCookiesInterceptor(context)
            val client = OkHttpClient.Builder()
                .addInterceptor(receivedCookiesInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
            return client
        }
        //기타 쿠키없을때 쓰는타입 ex)회원가입
        else {
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            return client
        }
    }

    //retrofit 선언
    fun retrofit(baseUrl: String, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // api baseUrl 연결
    fun serviceAPI(client: OkHttpClient): ServiceAPI =
        retrofit("http://10.0.2.2:5000/", client).create(ServiceAPI::class.java)


}
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
    fun getClient(): OkHttpClient{
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return client
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
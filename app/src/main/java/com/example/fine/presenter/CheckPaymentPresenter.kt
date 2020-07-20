package com.example.fine.presenter

import android.app.Activity
import android.content.Context
import com.example.fine.model.ServerData_case
import com.example.fine.model.createCase
import com.example.fine.network.RetrofitClient
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckPaymentPresenter: CheckPaymentContract.Presenter {
    override fun createCase(createCase: createCase) {
        // 서버로 상담사 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val createCase_request : Call<ServerData_case> = apiService.createCase(createCase)

        createCase_request.enqueue(object : Callback<ServerData_case> {
            override fun onFailure(call: Call<ServerData_case>, t: Throwable) {
                executionLog(TAG, "createCase_request 실패")
            }

            override fun onResponse(call: Call<ServerData_case>, response: Response<ServerData_case>) {
                if(response.isSuccessful){
                    executionLog(TAG, response.body()!!.message!!)
                    showMessage(response.body()!!.message!!)

                }
            }
        })
    }


    override lateinit var mView: CheckPaymentContract.View
    override lateinit var mContext: Context
    override val TAG: String = "CheckPaymentPresenter"
}
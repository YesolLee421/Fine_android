package com.example.fine.presenter

import android.app.Activity
import android.content.Context
import com.example.fine.model.ChangePassword
import com.example.fine.model.ServerData_mypage
import com.example.fine.network.RetrofitClient
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordPresenter() : ChangePasswordContract.Presenter {
    override fun saveInfo(changePassword: ChangePassword) {
        // 서버로 상담사 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val changeNickName_request : Call<ServerData_mypage> = apiService.changePassword(changePassword)
        changeNickName_request.enqueue(object : Callback<ServerData_mypage> {
            override fun onFailure(call: Call<ServerData_mypage>, t: Throwable) {
                executionLog(TAG, "changeNickName_request 실패")
                executionLog(TAG, t.message!!)
                showMessage(t.message)
            }

            override fun onResponse(call: Call<ServerData_mypage>, response: Response<ServerData_mypage>) {
                if(response.isSuccessful){
                    executionLog(TAG, "changeNickName_request 성공")
                    val data: ServerData_mypage = response.body()!!
                    showMessage(data.message)
                    if(data.success){
                        (mContext as Activity).finish()
                    }
                }
            }
        })    }

    override lateinit var mView: ChangePasswordContract.View
    override lateinit var mContext: Context
    override val TAG: String = "ChangePasswordPresenter"
}
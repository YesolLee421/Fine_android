package com.example.fine.presenter

import android.content.Context
import com.example.fine.model.ChangeBankAccount
import com.example.fine.model.CounselorData
import com.example.fine.model.ServerData_mypage
import com.example.fine.network.RetrofitClient
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeCounselorBankAccountPresenter() : ChangeCounselorBankAccountContract.Presenter {
    override fun saveInfo(changeBankAccount: ChangeBankAccount) {
        // 서버로 상담사 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val changeBankAccount_request : Call<ServerData_mypage> = apiService.changeBankAccount(changeBankAccount)
        changeBankAccount_request.enqueue(object : Callback<ServerData_mypage> {
            override fun onFailure(call: Call<ServerData_mypage>, t: Throwable) {
                executionLog(TAG, "changeBankAccount_request 실패")
            }
            override fun onResponse(call: Call<ServerData_mypage>, response: Response<ServerData_mypage>) {
                if(response.isSuccessful){
                    executionLog(TAG, "changeBankAccount_request 성공")
                    val data: ServerData_mypage = response.body()!!
                    if(data.data!=null){
                        showMessage(data.message)
                        val mypage = data.data
                        val counselor: CounselorData? = mypage?.counselor
                        if(counselor?.bank_account!=null){
                            val jsonObject = JSONObject(counselor.bank_account!!)
                            mView.showInfo(jsonObject.getString("bank_name"), jsonObject.getString("account_number"))
                        }
                    }
                }
            }
        })

    }

    override lateinit var mView: ChangeCounselorBankAccountContract.View
    override lateinit var mContext: Context
    override val TAG: String = "ChangeCounselorBankAccountPresenter"
}
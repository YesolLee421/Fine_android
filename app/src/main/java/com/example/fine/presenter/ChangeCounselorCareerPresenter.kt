package com.example.fine.presenter

import android.content.Context
import com.example.fine.model.ChangeExperience
import com.example.fine.model.ServerData_mypage
import com.example.fine.network.RetrofitClient
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeCounselorCareerPresenter() : ChangeCounselorCareerContract.Presenter {
    override fun saveInfo(changeExperience: ChangeExperience) {
        // 서버로 상담사 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val changeExperience_request : Call<ServerData_mypage> = apiService.changeExperience(changeExperience)
        changeExperience_request.enqueue(object : Callback<ServerData_mypage> {
            override fun onFailure(call: Call<ServerData_mypage>, t: Throwable) {
                executionLog(TAG, "changeExperience_request 실패")
            }

            override fun onResponse(call: Call<ServerData_mypage>, response: Response<ServerData_mypage>) {
                if(response.isSuccessful){
                    executionLog(TAG, "changeExperience_request 성공")
                    val data: ServerData_mypage = response.body()!!
                    if(data.data!=null){
                        executionLog(TAG, "changeExperience_request 성공")
                        showMessage(data.message)
                        val mypage = data.data
                        mView.showInfo(mypage?.counselor?.certificate, mypage?.counselor?.career, mypage?.counselor?.education)
                    }
                }
            }
        })
    }

    override lateinit var mView: ChangeCounselorCareerContract.View
    override lateinit var mContext: Context
    override val TAG: String = "ChangeCounselorCareerPresenter90"

}
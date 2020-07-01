package com.example.fine.presenter

import android.app.Activity
import android.app.job.JobInfo
import android.content.Context
import android.content.Intent
import com.example.fine.model.CounselorData
import com.example.fine.model.ServerData_counselor
import com.example.fine.network.RetrofitClient
import com.example.fine.view.activity.RequestCounselingActivity
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CounselorDetailPresenter: CounselorDetailContract.Presenter {

    override val TAG = "CounselorDetailPresenter"
    override lateinit var mView: CounselorDetailContract.View
    override lateinit var mContext: Context
    lateinit var counselorData: CounselorData


    override fun loadData(user_uid: String){
        // 서버로 상담사 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val getCounselor_request : Call<ServerData_counselor> = apiService.getCounselor(user_uid)

        getCounselor_request.enqueue(object : Callback<ServerData_counselor>{
            override fun onFailure(call: Call<ServerData_counselor>, t: Throwable) {
                executionLog(TAG, "getCounselor_request 실패")
            }

            override fun onResponse(call: Call<ServerData_counselor>, response: Response<ServerData_counselor>) {
                if(response.isSuccessful){
                    val data: ServerData_counselor = response.body()!!
                    if(data.data!=null){
                        counselorData= data.data!!
                        mView.showInfo(counselorData)
                    }
                    executionLog(TAG, data.message!!)
                    showMessage(data.message!!)
                }
            }
        })
    }

    fun setCount(count: Int) : String {
        when {
            count >= 2000 ->{
                return  "2000회 이상 도움"
            }
            count >= 1000 ->{
                return "1000회 이상 도움"
            }
            count >= 500 ->{
                return "500회 이상 도움"
            }
            count >= 200 -> {
                return "200회 이상 도움"
            }
            count >= 100 -> {
               return  "100회 이상 도움"
            }
            count >= 50 -> {
                return  "50회 이상 도움"
            }
            else -> {
                return ""
            }
        }
    }


    override fun startRequestCounselActivity() {
        showMessage("상담신청으로 이동")
        executionLog(TAG, "상담신청으로 이동")
        val intent: Intent = Intent(mContext, RequestCounselingActivity::class.java)
        intent.putExtra("counselor_id", counselorData.user_uid)
        intent.putExtra("price", counselorData.price )
        intent.putExtra("discount_4w", counselorData.discount_4w)
        intent.putExtra("discount_10w", counselorData.discount_10w)
        mContext.startActivity(intent)
        (mContext as Activity).finish()
    }
}
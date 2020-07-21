package com.example.fine.presenter

import android.content.Context
import android.content.Intent
import com.example.fine.model.CounselorData
import com.example.fine.model.ServerData_counselor
import com.example.fine.network.RetrofitClient
import com.example.fine.view.activity.ChatRoomActivity
import com.example.fine.view.activity.CheckPaymentActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestCounselingPresenter : RequestCounselingContract.Presenter {

    lateinit var counselorData: CounselorData

    override fun loadData(counselor_id: String) {
        // 서버로 상담사 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val getCounselor_request : Call<ServerData_counselor> = apiService.getCounselor(counselor_id)

        getCounselor_request.enqueue(object : Callback<ServerData_counselor> {
            override fun onFailure(call: Call<ServerData_counselor>, t: Throwable) {
                executionLog(TAG, "getCounselor_request 실패")
            }

            override fun onResponse(call: Call<ServerData_counselor>, response: Response<ServerData_counselor>) {
                if(response.isSuccessful){
                    executionLog(TAG, "getCounselor_request 성공")
                    val data: ServerData_counselor = response.body()!!
                    if(data.data!=null){
                        counselorData= data.data!!
                        mView.showInfo(counselorData.price, counselorData.discount_4w, counselorData.discount_10w)
                    }
                    executionLog(TAG, data.message!!)
                    showMessage(data.message!!)
                }
            }
        })
    }

    override fun startPaymentActivity(totalCount: Int, discountRate:Int) {
        showMessage("결제 확인창으로 이동")
        executionLog(TAG, "결제 확인창으로 이동")
        val intent = Intent(mContext, CheckPaymentActivity::class.java)
        intent.putExtra("counselor_id", counselorData.user_uid)
        intent.putExtra("price", counselorData.price)
        intent.putExtra("totalCount", totalCount)
        executionLog(TAG, "discountRate=${discountRate}")
        intent.putExtra("discountRate", discountRate)
        mContext.startActivity(intent)
    }

    override lateinit var mView: RequestCounselingContract.View
    override lateinit var mContext: Context
    override val TAG: String = "RequestCounselingPresenter"
}
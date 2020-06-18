package com.example.fine.presenter

import android.content.Context
import com.example.fine.model.ChangeTimePrefered
import com.example.fine.model.CounselorData
import com.example.fine.model.ServerData_mypage
import com.example.fine.network.RetrofitClient
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeCounselorTimePreferedPresenter : ChangeCounselorTimePreferedContract.Presenter {

    override fun saveInfo(changeTimePrefered: ChangeTimePrefered) {
        executionLog(TAG, changeTimePrefered.day.toString())
        executionLog(TAG, changeTimePrefered.time.toString())
        // 서버로 상담사 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val changeTimePrefered_request : Call<ServerData_mypage> = apiService.changeTimePrefered(changeTimePrefered)
        changeTimePrefered_request.enqueue(object : Callback<ServerData_mypage> {
            override fun onFailure(call: Call<ServerData_mypage>, t: Throwable) {
                executionLog(TAG, "changeTimePrefered_request 실패")
            }
            override fun onResponse(call: Call<ServerData_mypage>, response: Response<ServerData_mypage>) {
                if(response.isSuccessful){
                    executionLog(TAG, "changeTimePrefered_request 성공")
                    val data: ServerData_mypage = response.body()!!
                    if(data.data!=null){
                        showMessage(data.message)
                        val mypage = data.data
                        val counselor: CounselorData? = mypage?.counselor
                        if(counselor?.time_prefered!=null){
                            val jsonObject = JSONObject(counselor.time_prefered!!)
                            executionLog(TAG, "timePrefered_json = "+jsonObject)
                            val day: JSONArray = JSONArray(jsonObject.get("day").toString())
                            val time: JSONArray = JSONArray(jsonObject.get("time").toString())
                            mView.showInfo(day,time)
                        }
                    }
                }
            }
        })


    }

    override lateinit var mView: ChangeCounselorTimePreferedContract.View
    override lateinit var mContext: Context
    override val TAG: String = "ChangeCounselorTimePreferedPresenter"
}
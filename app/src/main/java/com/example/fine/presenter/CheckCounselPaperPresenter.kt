package com.example.fine.presenter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.fine.model.Paper
import com.example.fine.model.ServerData_counselor
import com.example.fine.model.ServerData_paper
import com.example.fine.model.userData
import com.example.fine.network.RetrofitClient
import com.example.fine.view.activity.WriteCounselPaperActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckCounselPaperPresenter : CheckCounselPaperContract.Presenter{

    var user: userData = userData("","","","",3, false)
    lateinit var preferences: SharedPreferences

    override fun getUser() {
        preferences = mContext.getSharedPreferences("USERSIGN", 0)
        user.user_uid = preferences.getString("user_uid", "")!!
        user.email = preferences.getString("user_email", "")!!
        user.nickname = preferences.getString("user_nickname","")!!
        user.type = preferences.getInt("user_type", 3)
    }

    override fun startWritePaperActivity(page: Int) {
        showMessage("상담 접수지 작성으로 이동")
        executionLog(TAG, "상담 접수지 작성으로 이동")
        val intent: Intent = Intent(mContext, WriteCounselPaperActivity::class.java)
        intent.putExtra("page", page)
        mContext.startActivity(intent)
    }

    override fun loadData(paper_id: Int) {
        // 서버로 상담접수지 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        // 유저 본인의 접수지 제출
        var getPaper_request : Call<ServerData_paper>? = null
        if(paper_id==-1){
            executionLog(TAG, "상담 접수지 제출 안 함")
            showMessage("상담 접수지 제출 안 함")
            return
        } else {
            if(user.type==3) {
                getPaper_request = apiService.getUserPaper()
            } else {
                getPaper_request = apiService.getCasePaper(paper_id)
            }
        }
        getPaper_request.enqueue(object : Callback<ServerData_paper> {
            override fun onFailure(call: Call<ServerData_paper>, t: Throwable) {
                executionLog(TAG, "getPaper_request 실패")
                executionLog(TAG, t.message.toString())
            }

            override fun onResponse(call: Call<ServerData_paper>, response: Response<ServerData_paper>) {
                if(response.isSuccessful){
                    executionLog(TAG, "getPaper_request 성공")
                    val result: ServerData_paper = response.body()!!
                    if(result.success){
                        executionLog(TAG, result.message!!)
                        showMessage(result.message!!)
                        if(result.data!=null){
                            mView.showInfo(result.data!!)
                        }
                    }
                }
            }

        })

    }

    override lateinit var mView: CheckCounselPaperContract.View
    override lateinit var mContext: Context
    override val TAG: String = "CheckCounselPaperPresenter"
}
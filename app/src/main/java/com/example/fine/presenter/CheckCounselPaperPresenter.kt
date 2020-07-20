package com.example.fine.presenter

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.fine.R
import com.example.fine.model.*
import com.example.fine.network.RetrofitClient
import com.example.fine.view.activity.WriteCounselPaperActivity
import kotlinx.android.synthetic.main.item_counsel_list.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckCounselPaperPresenter : CheckCounselPaperContract.Presenter{

    var user: userData = userData("","","","",3, false)
    lateinit var preferences: SharedPreferences
    var paper: Paper? = null

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
        if(user.type==3) {
            getPaper_request = apiService.getUserPaper()
        } else {
            if(paper_id==-1){
                executionLog(TAG, "상담 접수지 제출 안 함")
                showMessage("상담 접수지 제출 안 함")
                return
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
                            paper = result.data
                            mView.showInfo(result.data!!)
                        }
                    }
                }
            }
        })
    }

    fun loadCase() {
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val getCounselList_request : Call<ServerData_cases> = apiService.getCases()
        getCounselList_request.enqueue(object : Callback<ServerData_cases> {
            override fun onFailure(call: Call<ServerData_cases>, t: Throwable) {
                executionLog(TAG, "getCounselList_request 실패")
                executionLog(TAG, t.message!!)
            }
            override fun onResponse(call: Call<ServerData_cases>, response: Response<ServerData_cases>) {
                if(response.isSuccessful){
                    executionLog(TAG, "getCounselList_request 성공")
                    val result: ServerData_cases = response.body()!!
                    executionLog(TAG, result.toString())
                    if(result.success){
                        executionLog(TAG, result.message!!)
                        showMessage(result.message!!)
                        val list = result.data
                        var caseDetail : case_detail? = null
                        if(list!!.size!=0) {
                             caseDetail = list.get(0)
                        }
                        mView.showDialog(caseDetail)
                    }
                }
            }
        })
    }

    fun applyPaper(case_id: Int) {
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val setPaper_request : Call<ServerData_case> = apiService.setPaper(case_id)
        setPaper_request.enqueue(object : Callback<ServerData_case>{
            override fun onFailure(call: Call<ServerData_case>, t: Throwable) {
                executionLog(TAG, "setPaper_request 실패")
                executionLog(TAG, t.message!!)
            }
            override fun onResponse(call: Call<ServerData_case>, response: Response<ServerData_case>) {
                if(response.isSuccessful){
                    executionLog(TAG, "setPaper_request 성공")
                    val result: ServerData_case = response.body()!!
                    executionLog(TAG, result.toString())
                    if(result.success){
                        executionLog(TAG, result.message!!)
                        showMessage(result.message!!)
                    }
                }
            }
        })
    }



    override lateinit var mView: CheckCounselPaperContract.View
    override lateinit var mContext: Context
    override val TAG: String = "CheckCounselPaperPresenter"
}
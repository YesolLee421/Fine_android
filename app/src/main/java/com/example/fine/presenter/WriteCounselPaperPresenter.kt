package com.example.fine.presenter

import android.content.Context
import com.example.fine.model.Paper
import com.example.fine.model.ServerData_paper
import com.example.fine.network.RetrofitClient
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WriteCounselPaperPresenter : WriteCounselPaperContract.Presenter{
    override fun saveInfo(paper: Paper) {
        // 서버로 상담접수지 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        // 유저 본인의 접수지 조회
        val savePaper_request : Call<ServerData_paper> = apiService.changePaper()
        savePaper_request.enqueue(object : Callback<ServerData_paper> {
            override fun onFailure(call: Call<ServerData_paper>, t: Throwable) {
                executionLog(TAG, "savePaper_request 실패")
                executionLog(TAG, t.message.toString())
            }
            override fun onResponse(call: Call<ServerData_paper>, response: Response<ServerData_paper>) {
                if(response.isSuccessful){
                    executionLog(TAG, "savePaper_request 성공")
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

    override fun loadData() {
        // 서버로 상담접수지 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        // 유저 본인의 접수지 조회
        val getPaper_request : Call<ServerData_paper> = apiService.getUserPaper()
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
                            paper = result.data!!
                        }
                    }
                }
            }
        })
    }

    lateinit var paper: Paper
    override lateinit var mView: WriteCounselPaperContract.View
    override lateinit var mContext: Context
    override val TAG: String = "WriteCounselPaperPresenter"
}
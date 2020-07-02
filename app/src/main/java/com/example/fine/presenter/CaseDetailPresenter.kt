package com.example.fine.presenter

import android.content.Context
import android.content.Intent
import com.example.fine.model.ServerData_case
import com.example.fine.model.ServerData_cases
import com.example.fine.model.ServerData_counselor
import com.example.fine.network.RetrofitClient
import com.example.fine.view.activity.ChatRoomActivity
import com.example.fine.view.activity.CheckCounselPaperActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CaseDetailPresenter: CaseDetailContract.Presenter {
    override fun startCheckPaperActivity(paper_id: Int) {
        showMessage("상담 접수지 확인으로 이동")
        executionLog(TAG, "상담 접수지 확인으로 이동")
        val intent = Intent(mContext, CheckCounselPaperActivity::class.java)
        intent.putExtra("paper_id", paper_id)
        mContext.startActivity(intent)
    }

    override fun startChatRoomActivity() {
        showMessage("채팅 상담방으로 이동")
        executionLog(TAG, "채팅 상담방으로 이동")
        mContext.startActivity(Intent(mContext, ChatRoomActivity::class.java))
    }

    override fun loadData(case_id: Int) {
        // 서버로 상담사 정보 요청 보내기
        if(case_id!=-1){ // 인텐트 없는 기본값
            val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
            val apiService = RetrofitClient.serviceAPI(client)
            val getCase_request : Call<ServerData_case> = apiService.getCase(case_id)
            getCase_request.enqueue(object : Callback<ServerData_case> {
                override fun onFailure(call: Call<ServerData_case>, t: Throwable) {
                    executionLog(TAG, "getCase_request 실패")
                    executionLog(TAG, t.message.toString())
                }

                override fun onResponse(call: Call<ServerData_case>, response: Response<ServerData_case>) {
                    if(response.isSuccessful){
                        executionLog(TAG, "getCase_request 성공")
                        val result: ServerData_case = response.body()!!
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
        } else {
            executionLog(TAG, "getCase_request 실패: case_id 없음")
        }
    }

    override lateinit var mView: CaseDetailContract.View
    override lateinit var mContext: Context
    override val TAG: String = "CaseDetailPresenter"
}
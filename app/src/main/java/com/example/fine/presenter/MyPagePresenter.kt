package com.example.fine.presenter

import android.content.Context
import android.content.Intent
import com.example.fine.model.MyPageData
import com.example.fine.model.ServerData_mypage
import com.example.fine.network.RetrofitClient
import com.example.fine.view.activity.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPagePresenter(): MyPageContract.Presenter {

    val TAG = "MyPagePresenter"
    override lateinit var mView: MyPageContract.View
    override lateinit var mContext: Context


    override fun loadData() {
        // 서버로 상담사 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val getMyPage_request : Call<ServerData_mypage> = apiService.getMyPage()
        getMyPage_request.enqueue(object : Callback<ServerData_mypage>{
            override fun onFailure(call: Call<ServerData_mypage>, t: Throwable) {
                executionLog(TAG, "getMyPage_request 실패")
            }

            override fun onResponse(call: Call<ServerData_mypage>, response: Response<ServerData_mypage>) {
                if(response.isSuccessful){
                    val data: ServerData_mypage = response.body()!!
                    if(data.data!=null){
                        executionLog(TAG, "getMyPage_request 성공")
                        val mypage = data.data
                        mView.showInfo(mypage?.user!!)
                    }
                }
            }
        })
    }

    override fun startChangeNickNameActivity() {
        // 여기는 startActivityOnResult()로 구현하는 것이 나을듯
        showMessage("닉네임변경으로 이동")
        executionLog(TAG, "닉네임변경으로 이동")
//        val intent: Intent = Intent(mContext, ChangeNickNameActivity::class.java)
//
//        mContext.startActivity(intent)
    }

    override fun startChangePasswordActivity() {
        // 여기는 startActivityOnResult()로 구현하는 것이 나을듯
        showMessage("비밀번호 변경으로 이동")
        executionLog(TAG, "비밀번호 변경으로 이동")
    }

    override fun startCheckPaperActivity() {
        showMessage("상담접수지로 이동")
        executionLog(TAG, "상담접수지로 이동")
        val intent: Intent = Intent(mContext, CheckCounselPaperActivity::class.java)
        mContext.startActivity(intent)
    }

    override fun startNoticeActivity() {
        showMessage("알림(상담동의서) 이동")
        executionLog(TAG, "알림(상담동의서) 이동")
        val intent: Intent = Intent(mContext, NoticeActivity::class.java)
        mContext.startActivity(intent)
    }

    override fun startCounselListActivity() {
        showMessage("상담접수지로 이동")
        executionLog(TAG, "상담접수지로 이동")
        val intent: Intent = Intent(mContext, CounselListActivity::class.java)
        mContext.startActivity(intent)
    }

    override fun startSettingsActivity() {
        showMessage("앱 설정으로 이동")
        executionLog(TAG, "앱 설정으로 이동")
        val intent: Intent = Intent(mContext, SettingsActivity::class.java)
        mContext.startActivity(intent)
    }


}
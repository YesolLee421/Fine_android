package com.example.fine.presenter

import android.content.Context
import android.content.Intent
import com.example.fine.contract.MyPageCounselorContract
import com.example.fine.model.ServerData_mypage
import com.example.fine.network.RetrofitClient
import com.example.fine.view.activity.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageCounselorPresenter() : MyPageCounselorContract.Presenter {

    override fun loadData() {
        // 서버로 상담사 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val getMyPage_request : Call<ServerData_mypage> = apiService.getMyPage()
        getMyPage_request.enqueue(object : Callback<ServerData_mypage> {
            override fun onFailure(call: Call<ServerData_mypage>, t: Throwable) {
                executionLog(TAG, "getMyPage_request 실패")
            }

            override fun onResponse(call: Call<ServerData_mypage>, response: Response<ServerData_mypage>) {
                if(response.isSuccessful){
                    val data: ServerData_mypage = response.body()!!
                    if(data.data!=null){
                        executionLog(TAG, "getMyPage_request 성공")
                        val mypage = data.data
                        mView.showInfo(mypage?.user!!, mypage.counselor!!)
                    }
                }
            }
        })
    }

    override fun startChangeProfileActivity() {
        showMessage("프로필 변경으로 이동")
        executionLog(TAG, "프로필 변경으로 이동")
    }

    override fun startChangePasswordActivity() {
        showMessage("비밀번호 변경으로 이동")
        executionLog(TAG, "비밀번호 변경으로 이동")
    }

    override fun startChangeIntroActivity() {
        showMessage("상담사소개 변경으로 이동")
        executionLog(TAG, "상담사소개 변경으로 이동")
        val intent: Intent = Intent(mContext, ChangeCounselorIntroActivity::class.java)
        mContext.startActivity(intent)
    }

    override fun startChangeCareerActivity() {
        showMessage("상담사약력 변경으로 이동")
        executionLog(TAG, "상담사약력 변경으로 이동")
        val intent: Intent = Intent(mContext, ChangeCounselorIntroActivity::class.java)
        mContext.startActivity(intent)
    }

    override fun startChangeTimePreferedActivity() {
        showMessage("선호시간 변경으로 이동")
        executionLog(TAG, "선호시간 변경으로 이동")
        val intent: Intent = Intent(mContext, ChangeCounselorIntroActivity::class.java)
        mContext.startActivity(intent)    }

    override fun startChangePriceActivity() {
        showMessage("상담 가격 변경으로 이동")
        executionLog(TAG, "상담 가격 변경으로 이동")
        val intent: Intent = Intent(mContext, ChangeCounselorIntroActivity::class.java)
        mContext.startActivity(intent)    }

    override fun startChangeBankAccountActivity() {
        showMessage("계좌번호 변경으로 이동")
        executionLog(TAG, "계좌번호 변경으로 이동")
        val intent: Intent = Intent(mContext, ChangeCounselorIntroActivity::class.java)
        mContext.startActivity(intent)    }

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

    override lateinit var mView: MyPageCounselorContract.View
    override lateinit var mContext: Context
    val TAG = "MyPageCounselorPresenter"

}
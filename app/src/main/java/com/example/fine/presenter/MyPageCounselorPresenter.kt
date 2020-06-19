package com.example.fine.presenter

import android.content.Context
import android.content.Intent
import com.example.fine.contract.MyPageCounselorContract
import com.example.fine.model.MyPageData
import com.example.fine.model.ServerData_mypage
import com.example.fine.network.RetrofitClient
import com.example.fine.view.activity.*
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageCounselorPresenter() : MyPageCounselorContract.Presenter {

    lateinit var mypage: MyPageData

    override fun loadData() {
        // 서버로 상담사 정보 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val getMyPageCounselor_request : Call<ServerData_mypage> = apiService.getMyPage()
        getMyPageCounselor_request.enqueue(object : Callback<ServerData_mypage> {
            override fun onFailure(call: Call<ServerData_mypage>, t: Throwable) {
                executionLog(TAG, "getMyPageCounselor_request 실패")
            }

            override fun onResponse(call: Call<ServerData_mypage>, response: Response<ServerData_mypage>) {
                if(response.isSuccessful){
                    val data: ServerData_mypage = response.body()!!
                    if(data.data!=null){
                        executionLog(TAG, "getMyPageCounselor_request 성공")
                        val mypage_data = data.data
                        mypage = MyPageData(mypage_data?.user, mypage_data?.counselor)
                        mView.showInfo(mypage.user!!, mypage.counselor!!)
                    }
                }
            }
        })
    }

    override fun startChangeProfileActivity() {
        showMessage("프로필 변경으로 이동")
        executionLog(TAG, "프로필 변경으로 이동")
        val intent = Intent(mContext, ChangeCounselorProfileActivity::class.java)
        intent.putExtra("name_formal", mypage.counselor?.name_formal)
        intent.putExtra("description", mypage.counselor?.description)
        intent.putExtra("picture", mypage.counselor?.picture)
        intent.putExtra("gender", mypage.counselor?.gender)
        intent.putExtra("count", mypage.counselor?.count)
        intent.putExtra("isVerified", mypage.counselor?.isVerified)
        mContext.startActivity(intent)
    }

    override fun startChangePasswordActivity() {
        // 여기는 startActivityOnResult()로 구현하는 것이 나을듯
        showMessage("비밀번호 변경으로 이동")
        executionLog(TAG, "비밀번호 변경으로 이동")
        mContext.startActivity(Intent(mContext, ChangePasswordActivity::class.java))
    }

    override fun startChangeIntroActivity() {
        showMessage("상담사소개 변경으로 이동")
        executionLog(TAG, "상담사소개 변경으로 이동")
        val intent = Intent(mContext, ChangeCounselorIntroActivity::class.java)
        intent.putExtra("intro_1", mypage.counselor?.intro_1)
        intent.putExtra("intro_2", mypage.counselor?.intro_2)
        intent.putExtra("intro_3", mypage.counselor?.intro_3)
        mContext.startActivity(intent)
    }

    override fun startChangeCareerActivity() {
        showMessage("상담사약력 변경으로 이동")
        executionLog(TAG, "상담사약력 변경으로 이동")
        val intent = Intent(mContext, ChangeCounselorCareerActivity::class.java)
        intent.putExtra("certificate", mypage.counselor?.certificate)
        intent.putExtra("career", mypage.counselor?.career)
        intent.putExtra("education", mypage.counselor?.education)
        mContext.startActivity(intent)
    }

    override fun startChangeTimePreferedActivity() {
        showMessage("선호시간 변경으로 이동")
        executionLog(TAG, "선호시간 변경으로 이동")
        val intent = Intent(mContext, ChangeCounselorTimePreferedActivity::class.java)
        intent.putExtra("time_prefered", mypage.counselor?.time_prefered)
        mContext.startActivity(intent)
    }

    override fun startChangePriceActivity() {
        showMessage("상담 가격 변경으로 이동")
        executionLog(TAG, "상담 가격 변경으로 이동")
        val intent: Intent = Intent(mContext, ChangeCounselorPriceActivity::class.java)
        intent.putExtra("price", mypage.counselor?.price)
        intent.putExtra("discount_4w", mypage.counselor?.discount_4w)
        intent.putExtra("discount_10w", mypage.counselor?.discount_10w)
        mContext.startActivity(intent)
    }

    override fun startChangeBankAccountActivity() {
        showMessage("계좌번호 변경으로 이동")
        executionLog(TAG, "계좌번호 변경으로 이동")
        val intent = Intent(mContext, ChangeCounselorBankAccountActivity::class.java)
        val counselor = mypage.counselor
        if(counselor?.bank_account!=null){
            val jsonObject = JSONObject(counselor.bank_account!!)
            intent.putExtra("bank_name", jsonObject.getString("bank_name"))
            intent.putExtra("account_number", jsonObject.getString("account_number"))
        }
        mContext.startActivity(intent)
    }

    override fun startCheckPaperActivity() {
        showMessage("상담접수지로 이동")
        executionLog(TAG, "상담접수지로 이동")
        val intent = Intent(mContext, CheckCounselPaperActivity::class.java)
        intent.putExtra("user_uid", mypage.user?.user_uid) // fk_user_uid로 찾을 수 있도록
        mContext.startActivity(intent)
    }

    override fun startNoticeActivity() {
        showMessage("알림(상담동의서) 이동")
        executionLog(TAG, "알림(상담동의서) 이동")
        val intent: Intent = Intent(mContext, NoticeActivity::class.java)
        mContext.startActivity(intent)
    }

    override fun startCounselListActivity() {
        showMessage("상담 목록으로 이동")
        executionLog(TAG, "상담 목록으로 이동")
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
    override val TAG = "MyPageCounselorPresenter"

}
package com.example.fine.presenter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.fine.adapter.CounselListAdapter
import com.example.fine.model.ServerData_cases
import com.example.fine.model.case_detail
import com.example.fine.model.userData
import com.example.fine.network.RetrofitClient
import com.example.fine.view.activity.MyPageActivity
import com.example.fine.view.activity.MyPageCounselorActivity
import com.example.fine.view.activity.SearchCounselorActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CounselListPresenter(): CounselListContract.Presenter {
    override fun getUser() {
        preferences = mContext.getSharedPreferences("USERSIGN", 0)

        user.user_uid = preferences.getString("user_uid", "")!!
        user.email = preferences.getString("user_email", "")!!
        //user.password = preferences.getString("user_password", "")!!
        user.nickname = preferences.getString("user_nickname","")!!
        user.type = preferences.getInt("user_type", 3)
    }

    lateinit var preferences: SharedPreferences
    var user: userData = userData("","","","",3, false)

    override fun startMypageActivity() {
        showMessage("마이페이지로 이동 type = ${user.type}")
        executionLog(TAG, "마이페이지로 이동")
        lateinit var intent: Intent

        when(user.type){
            1-> showMessage("관리자 마이페이지 미구현")
            2-> intent = Intent(mContext, MyPageCounselorActivity::class.java)
            3-> intent = Intent(mContext, MyPageActivity::class.java)
        }
        mContext.startActivity(intent)
    }

    override fun startCounselorListActivity() {
        showMessage("상담사 리스트로 이동")
        executionLog(TAG, "상담사 리스트로 이동")
        mContext.startActivity(Intent(mContext, SearchCounselorActivity::class.java))
    }

    override fun clearItems(list: ArrayList<case_detail?>, adapter: CounselListAdapter) {
        adapter.arrayList.clear()
    }

    override fun loadItems(list: ArrayList<case_detail?>, adapter: CounselListAdapter) {
        /* 가져와야 할 정보
        * Case: case_id / counselor_id/ hasPaper / status / totalCase / nextCase..
        * counselor: name_formal
        *
        * */
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
                        for(element in result.data!!){
                            adapter.addItem(element)
                        }
                        adapter.notifyDataSetChanged()
                        executionLog(TAG, result.message!!)
                        showMessage(result.message!!)
                    }


                }
            }
        })
    }

    override lateinit var mView: CounselListContract.View
    override lateinit var mContext: Context
    override val TAG: String = "CounselListPresenter"
}
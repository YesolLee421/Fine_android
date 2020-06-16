package com.example.fine.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.fine.adapter.SearchCounselorAdapter
import com.example.fine.model.CounselorData
import com.example.fine.model.ServerData_counselors
import com.example.fine.model.userData
import com.example.fine.network.RetrofitClient
import com.example.fine.view.activity.CounselListActivity
import com.example.fine.view.activity.MyPageActivity
import com.example.fine.view.activity.MyPageCounselorActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchCounselorPresenter() : SearchCounselorContract.Presenter {

    val TAG = "SearchCounselorPresenter"
    override lateinit var mView: SearchCounselorContract.View
    override lateinit var mContext: Context
    lateinit var preferences: SharedPreferences
    var user: userData = userData("","","","",3, false)

    val sort : Int = 0
    val filter : Int = 0;

    override fun clearItems(list: ArrayList<CounselorData?>, adapter: SearchCounselorAdapter){
        adapter.arrayList.clear()
    }

    override fun loadItems(list: ArrayList<CounselorData?>, adapter: SearchCounselorAdapter) {
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val getCounselors_request : Call<ServerData_counselors> = apiService.getCounselorsList(sort, filter)
        getCounselors_request.enqueue(object : Callback<ServerData_counselors>{
            override fun onFailure(call: Call<ServerData_counselors>, t: Throwable) {
                executionLog(TAG, "getCounselors_request 실패")
            }

            override fun onResponse(call: Call<ServerData_counselors>, response: Response<ServerData_counselors>) {
                if(response.isSuccessful){

                    val data : ServerData_counselors = response.body()!!
                    executionLog(TAG, data.toString())
                    if(data.success){
                        for(element in data.data!!){
                            executionLog(TAG, element.name_formal!!)
                            adapter.addItem(element)
                        }
                    }
                    adapter.notifyDataSetChanged()
                    executionLog(TAG, data.message!!)
                    showMessage(data.message!!)
                }
            }
        })
    }

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

    override fun startCounselListActivity() {
        showMessage("상담 리스트로 이동")
        executionLog(TAG, "상담 리스트로 이동")
        mContext.startActivity(Intent(mContext, CounselListActivity::class.java))

    }

    override fun getUser(){
        preferences = mContext.getSharedPreferences("USERSIGN", 0)

        user.user_uid = preferences.getString("user_uid", "")!!
        user.email = preferences.getString("user_email", "")!!
        //user.password = preferences.getString("user_password", "")!!
        user.nickname = preferences.getString("user_nickname","")!!
        user.type = preferences.getInt("user_type", 3)
    }

}
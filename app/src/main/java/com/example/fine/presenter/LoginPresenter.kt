package com.example.fine.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.fine.model.Login
import com.example.fine.model.Register
import com.example.fine.model.ServerData
import com.example.fine.network.RetrofitClient
import com.example.fine.view.activity.LoginActivity
import com.example.fine.view.activity.RegisterActivity
import com.example.fine.view.activity.SearchCounselorActivity
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter() : LoginContract.Presenter{
    val TAG = "LoginPresenter"
    override lateinit var mView: LoginContract.View
    override lateinit var mContext: Context

    override fun login(email: String, password: String) {
        // 서버로 로그인 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient()
        val apiService = RetrofitClient.serviceAPI(client)
        val loginData: Login = Login(email, password)
        executionLog(TAG,"email="+email+" / password="+password)

        val login_request: Call<JsonObject> = apiService.login(loginData)
        login_request.enqueue(object : Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                executionLog(TAG, "login_request 실패")
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                // 성공
                if(response.isSuccessful){ // success / data (string) / messege
                    val jsonData : JSONObject = JSONObject(Gson().toJson(response.body()))
                    //val serverData: ServerData = Gson().fromJson(response.body(), ServerData::class.java)
                    if(jsonData.getBoolean("success")){
                        startSearchCounselorActivity()
                    }
                    showMessage(jsonData.getString("message"))
                }
            }

        })
    }

    override fun startRegisterActivity() {
        showMessage("회원가입으로 이동")
        executionLog(TAG, "회원가입으로 이동")
        mContext.startActivity(Intent(mContext, RegisterActivity::class.java))
        (mContext as Activity).finish()    }

    override fun startSearchCounselorActivity() {
        showMessage("상담사찾기로 이동")
        executionLog(TAG, "상담사찾기로 이동")
        mContext.startActivity(Intent(mContext, SearchCounselorActivity::class.java))
        (mContext as Activity).finish()
    }


}
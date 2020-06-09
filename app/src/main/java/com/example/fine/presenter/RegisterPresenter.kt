package com.example.fine.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.example.fine.model.Register
import com.example.fine.model.ServerData
import com.example.fine.model.userData
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
import java.util.*

class RegisterPresenter() : RegisterContract.Presenter {

    override fun register(email: String, password: String, nickname: String, type: Int) {
        // 서버로 회원가입 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient()
        val apiService = RetrofitClient.serviceAPI(client)
        val registerData: Register = Register(email, password, nickname, type)
        executionLog(TAG,"email="+email+" / password="+password+" / nickname="+ nickname+" / type="+type)

        val register_request: Call<JsonObject> = apiService.register(registerData)
        register_request.enqueue(object: Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                executionLog(TAG, "register_request 실패")
                // 실패
            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                // 성공
                if(response.isSuccessful){ // success / data (string) / messege
                    val jsonData :JSONObject = JSONObject(Gson().toJson(response.body()))
                    //val serverData: ServerData = Gson().fromJson(response.body(), ServerData::class.java)
                    if(jsonData.getBoolean("success")){
                        startLoginActivity()
                    }
                    showMessage(jsonData.getString("message"))
                }
            }
        })
    }

    val TAG = "RegisterPresenter"

    override lateinit var mView: RegisterContract.View
    override lateinit var mContext: Context

    override fun emailCheck(email: String) {
    }

    override fun nicknameCheck(nickname: String) {
    }



    override fun startLoginActivity() {
        showMessage("로그인으로 이동")
        executionLog(TAG, "로그인으로 이동")
        mContext.startActivity(Intent(mContext, LoginActivity::class.java))
        (mContext as Activity).finish()
    }

    override fun startSearchCounselorActivity() {
        showMessage("상담사찾기로 이동")
        executionLog(TAG, "상담사찾기로 이동")
        mContext.startActivity(Intent(mContext, SearchCounselorActivity::class.java))
        (mContext as Activity).finish()
    }







}
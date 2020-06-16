package com.example.fine.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.fine.model.Login
import com.example.fine.network.RetrofitClient
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
    internal var preferences: SharedPreferences? = null

    override fun login(email: String, password: String) {
        // 서버로 로그인 요청 보내기
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "receiveCookie")
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
                    val jsonData = JSONObject(Gson().toJson(response.body()))
                    //val userJson = JsonParser().parse(jsonData.getString("data"))
                    // 추후 여기서 받아온 user 데이터 mapping해서 볼 수 있도록 생각하기
                    if(jsonData.getBoolean("success")){
                        val user: JSONObject = jsonData.getJSONObject("data")

                        preferences = mContext.getSharedPreferences("USERSIGN", 0)
                        val editor = preferences!!.edit()
                        editor.putString("user_uid", user.getString("user_uid"))
                        editor.putString("user_email", user.getString("email"))
                        //editor.putString("user_password", user.getString("password"))
                        editor.putString("user_nickname", user.getString("nickname"))
                        editor.putInt("user_type", user.getInt("type"))
                        editor.apply()
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
        (mContext as Activity).finish()
    }

    override fun startSearchCounselorActivity() {
        showMessage("상담사찾기로 이동")
        executionLog(TAG, "상담사찾기로 이동")
        mContext.startActivity(Intent(mContext, SearchCounselorActivity::class.java))
        (mContext as Activity).finish()
    }


}
package com.example.fine.presenter

import android.content.Context
import android.content.SharedPreferences
import com.example.fine.adapter.ChatRoomAdapter
import com.example.fine.adapter.CounselListAdapter
import com.example.fine.model.*
import com.example.fine.network.RetrofitClient
import com.github.nkzawa.socketio.client.Socket
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatRoomPresenter : ChatRoomContract.Presenter{
    override lateinit var mView: ChatRoomContract.View
    override lateinit var mContext: Context
    override val TAG: String = "ChatRoomPresenter"
    lateinit var preferences: SharedPreferences
    var user: userData = userData("","","","",3, false)




    fun getUser() {
        preferences = mContext.getSharedPreferences("USERSIGN", 0)

        user.user_uid = preferences.getString("user_uid", "")!!
        user.email = preferences.getString("user_email", "")!!
        //user.password = preferences.getString("user_password", "")!!
        user.nickname = preferences.getString("user_nickname","")!!
        user.type = preferences.getInt("user_type", 3)
    }



    fun clearItems(adapter: ChatRoomAdapter) {
        adapter.clearItem()
    }

    fun loadItems(case_id: Int, adapter: ChatRoomAdapter) {
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val getMessage_request : Call<ServerData_messages> = apiService.getMessages(case_id)
        getMessage_request.enqueue(object : Callback<ServerData_messages>{
            override fun onFailure(call: Call<ServerData_messages>, t: Throwable) {
                executionLog(TAG, "getMessage_request 실패")
                executionLog(TAG, t.message!!)
            }

            override fun onResponse(call: Call<ServerData_messages>, response: Response<ServerData_messages>) {
                if(response.isSuccessful){
                    executionLog(TAG, "getMessage_request 성공")
                    val result: ServerData_messages = response.body()!!
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

    fun sendMessage(message: String) {

    }






    // 채팅 보내기
    // 채팅 받기

}